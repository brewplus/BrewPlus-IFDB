package jmash;

import java.util.ArrayList;
import java.util.List;

import jmash.PalmerRecommendedRange.PalmerRecommendedRangeType;

public class RicettaUtils {

	public static final double percDistilledROMash = 0.0;
	public static final double percDistilledROSparge = 0.0;

	public static PHResult calculatePH(Ricetta recipe) {
		Double pH = Double.NaN;
		List<Malt> recipeMalts = recipe.maltTableModel.getRows();

		List<Double> maltsPHfromChart = new ArrayList<Double>();

		Double mediaPesataPH = 0.0;

		double totalGrainWeightKg = getTotalGrainWeightKg(recipe);
		double totalGrainWeightLbs = getTotalGrainWeighLbs(recipe);

		double totalAcidGrainWeightGr = 0.0;

		for (Malt recipeMalt : recipeMalts) {

			MaltCategory maltCategory = findMaltCategory(recipeMalt);

			if (maltCategory == null || !maltCategory.isAcidMalt()) {
				Double lovibond = (recipeMalt.getSrm() + 0.6) / 1.3546;
						
				Double pHFromChart =  maltCategory != null ? (maltCategory.isCrystal() ? 5.22 - (0.00504 * lovibond / 1000) : maltCategory.getPH()) : MaltCategory.PH_DEFAULT;
				maltsPHfromChart.add(pHFromChart);

				Double phPesata = pHFromChart * (recipeMalt.getGrammi() / 1000);
				mediaPesataPH += phPesata;

			} else {
				// SE MALTO ACIDO LO TOLGO DAL CALCOLO DEI KG TOTALI
				totalGrainWeightKg -= (recipeMalt.getGrammi() / 1000);
				totalAcidGrainWeightGr += recipeMalt.getGrammi();
			}

		}

		mediaPesataPH = mediaPesataPH / totalGrainWeightKg;
		Double effectiveAlcalinity = calculateEffectiveAlcalinity(recipe);
		double residualAlcalinity = calculateResidualAlcalinity(recipe, effectiveAlcalinity);
		double mashVolumeGalloni = getMashVolumeGalloni(recipe);

		pH = mediaPesataPH + (0.1085 * mashVolumeGalloni / totalGrainWeightLbs + 0.013) * residualAlcalinity / 50;

		PHResult phResult = new PHResult();
		phResult.setAlk(effectiveAlcalinity);
		phResult.setRA(residualAlcalinity);
		phResult.setpH(pH);
		phResult.setTotalAcidGrainWeightGr(totalAcidGrainWeightGr);

		return phResult;
	}

	public static double getTotalGrainWeightKg(Ricetta recipe) {

		double totalGrainWeightGr = recipe.maltTableModel.getTotGrammi();

		for (Malt recipeMalt : recipe.maltTableModel.getRows()) {

			MaltCategory maltCategory = findMaltCategory(recipeMalt);

			if (maltCategory != null && maltCategory.isAcidMalt()) {
				totalGrainWeightGr -= recipeMalt.getGrammi();
			}
		}

		return totalGrainWeightGr / 1000.0;
	}

	public static double getTotalGrainWeighLbs(Ricetta recipe) {
		return getTotalGrainWeightKg(recipe) * 2.20462;
	}

	public static double calculateEffectiveAlcalinity(Ricetta recipe) {

		Double effectiveAlcalinity = Double.NaN;

		try {

			WaterAdjustPanel waterAdjustPanel = recipe.waterPanel;

			double mashVolumeGalloni = getMashVolumeGalloni(recipe);

			double volumeMashLitri = getMashVolumeLitri(recipe);
			double volumeSpargeLitri = getSpargeVolumeLitri(recipe);
			double volumeTotaleLitri = volumeMashLitri + volumeSpargeLitri;

			double mashRatio = volumeTotaleLitri > 0.0 ? volumeMashLitri / volumeTotaleLitri : 0.0;

			double mlAcidLactic = waterAdjustPanel.getLacticAcid();
			double percLacticAcidContent = waterAdjustPanel.getLacticAcidContent() / 100;
			double grCitrusAcid = waterAdjustPanel.getCitrusAcid();
			double percCitrusAcidContent = waterAdjustPanel.getCitrusAcidContent() / 100;
			double percAcidulatedMaltContent = waterAdjustPanel.getAcidulatedMaltContent() / 100.0;

			double waterCarbStart = waterAdjustPanel.getCarb();

			double adjustBicarbonatoDiSodioMash = waterAdjustPanel.getSoda() * mashRatio;
			double adjustCarbonatoDiCalcioMash = waterAdjustPanel.getChalk() * mashRatio;
			double adjustIdrossidoDiCalcioMash = waterAdjustPanel.getSlakedLime() * mashRatio;

			List<Malt> recipeMalts = recipe.maltTableModel.getRows();

			double acidMaltGramms = 0.0;
			double acidMaltOz = 0.0;

			for (Malt recipeMalt : recipeMalts) {
				MaltCategory recipeMalCategory = findMaltCategory(recipeMalt);
				if (recipeMalCategory != null && recipeMalCategory.isAcidMalt()) {
					acidMaltGramms += recipeMalt.getGrammi();
				}
			}
			acidMaltOz = acidMaltGramms / 28.34952;

			effectiveAlcalinity = ((1.0 - percDistilledROMash) * waterCarbStart * (50.0 / 61.0))
					+ ((adjustCarbonatoDiCalcioMash * 130.0)
							+ (((adjustBicarbonatoDiSodioMash * 157.0)
									- (176.1 * (mlAcidLactic * percLacticAcidContent
											+ grCitrusAcid * percCitrusAcidContent) * 2.0)
									- (4160.4 * acidMaltOz * percAcidulatedMaltContent * 2.5)
									+ (adjustIdrossidoDiCalcioMash * 357.0)) / mashVolumeGalloni));
		} catch (Exception e) {
			effectiveAlcalinity = Double.NaN;
		}
		return effectiveAlcalinity;
	}

	public static double calculateResidualAlcalinity(Ricetta recipe) {
		return calculateResidualAlcalinity(recipe, null);
	}

	public static double calculateResidualAlcalinity(Ricetta recipe, Double effectiveAlcalinity) {

		Double residualAlcalinity = Double.NaN;

		try {
			effectiveAlcalinity = effectiveAlcalinity != null && !effectiveAlcalinity.isNaN() ? effectiveAlcalinity
					: calculateEffectiveAlcalinity(recipe);

			double mashWaterProfileCalcium = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_CALCIUM);
			double mashWaterProfileMagnesium = getResultingWaterProfile(recipe,
					ResultingWaterProfileType.MASH_MAGNESIUM);

			residualAlcalinity = effectiveAlcalinity
					- ((mashWaterProfileCalcium / 1.4) + (mashWaterProfileMagnesium / 1.7));
		} catch (Exception e) {
			residualAlcalinity = Double.NaN;
		}

		return residualAlcalinity;
	}

	public static MaltType findMaltType(Malt malt) {
		MaltType maltType = null;
		if (malt != null) {
			List<MaltType> maltTypes = Gui.maltPickerTableModel.getRows();
			String maltName = malt.getNome();

			for (MaltType tmpMaltType : maltTypes) {
				if (maltName.equals(tmpMaltType.getNome())) {
					maltType = tmpMaltType;
					break;
				}
			}
		}

		return maltType;
	}

	public static double getMashVolumeLitri(Ricetta recipe) {
		// WaterNeeded waterNeeded = recipe.waterNeeded;
		WaterNeeded waterNeeded = recipe.waterNeeded;
		return waterNeeded.getMashVolume();
	}

	public static double getMashVolumeGalloni(Ricetta recipe) {
		double mashVolume = getMashVolumeLitri(recipe);
		double mashVolumeGalloni = mashVolume / 3.785412;

		return mashVolumeGalloni;
	}

	public static double getSpargeVolumeLitri(Ricetta recipe) {
		// WaterNeeded waterNeeded = recipe.waterNeeded;
		WaterNeeded waterNeeded = recipe.waterNeeded;
		return waterNeeded.getSpargeVolume();
	}

	public static double getSpargeVolumeGalloni(Ricetta recipe) {
		double spargeVolume = getSpargeVolumeLitri(recipe);
		double spargeVolumeGalloni = spargeVolume / 3.785412;

		return spargeVolumeGalloni;
	}

	public static MaltCategory findMaltCategory(MaltType maltType) {
		MaltCategory maltCategory = null;
		if (maltType != null) {
			List<MaltCategory> maltCategories = Gui.maltCategoryPickerTableModel.getRows();

			String maltTypeCategoryCode = maltType.getCategoria();
			if (maltTypeCategoryCode != null) {
				for (MaltCategory tmpMaltCategory : maltCategories) {
					if (maltTypeCategoryCode.equals(tmpMaltCategory.getCodice())) {
						maltCategory = tmpMaltCategory;
						break;
					}
				}
			}
		}
		return maltCategory;
	}

	public static MaltCategory findMaltCategory(Malt malt) {
		MaltType maltType = findMaltType(malt);
		return findMaltCategory(maltType);
	}

	public static Double[] getPalmerRecommendedRange(PalmerRecommendedRangeType type) {
		return PalmerRecommendedRange.rangesMap.get(type);
	}

	public static Double getResultingWaterProfile(Ricetta recipe, ResultingWaterProfileType type) {
		return getResultingWaterProfile(recipe, type, 0.0);
	}

	public static Double getResultingWaterProfile(Ricetta recipe, ResultingWaterProfileType type, Double defaultValue) {

		Double wp = defaultValue;

		WaterAdjustPanel waterAdjustPanel = recipe.waterPanel;

		double volumeMashLitri = getMashVolumeLitri(recipe);
		double volumeSpargeLitri = getSpargeVolumeLitri(recipe);
		double volumeTotaleLitri = volumeMashLitri + volumeSpargeLitri;
		
		double volumeMashGalloni = getMashVolumeGalloni(recipe);
		double volumeSpargeGalloni = getSpargeVolumeGalloni(recipe);
		double volumeTotaleGalloni = volumeMashGalloni + volumeSpargeGalloni;

		double mashRatio = volumeTotaleLitri > 0.0 ? volumeMashLitri / volumeTotaleLitri : 0.0;
		// double spargeRatio = volumeTotaleLitri > 0.0 ? volumeSpargeLitri /
		// volumeTotaleLitri : 0.0;

		double adjustCarbonatoDiCalcio = waterAdjustPanel.getChalk();
		double adjustGypsum = waterAdjustPanel.getGypsum();
		double adjustCloruroDiCalcio = waterAdjustPanel.getCaCl2();
		double adjustIdrossidoDiCalcio = waterAdjustPanel.getSlakedLime();
		double adjustEpsom = waterAdjustPanel.getEpsom();
		double adjustBicarbonatoDiSodio = waterAdjustPanel.getSoda();
		double adjustCloruroDiSodio = waterAdjustPanel.getNaCl();
		
		double mashRatioCarbonatoDiCalcio = waterAdjustPanel.useChalkSparge() ? mashRatio : 1.0;
		double mashRatioGypsum = waterAdjustPanel.useGypsumSparge() ? mashRatio : 1.0;
		double mashRatioCloruroDiCalcio = waterAdjustPanel.useCaCl2Sparge() ? mashRatio : 1.0;
		double mashRatioIdrossidoDiCalcio = waterAdjustPanel.useSlakedLimeSparge() ? mashRatio : 1.0;
		double mashRatioEpsom = waterAdjustPanel.useEpsomSparge() ? mashRatio : 1.0;
		double mashRatioBicarbonatoDiSodio = waterAdjustPanel.useSodaSparge() ? mashRatio : 1.0;
		double mashRatioCloruroDiSodio = waterAdjustPanel.useNaClSparge() ? mashRatio : 1.0;
		
		
		double adjustCarbonatoDiCalcioMash = adjustCarbonatoDiCalcio * mashRatioCarbonatoDiCalcio;
		double adjustGypsumMash = adjustGypsum * mashRatioGypsum;
		double adjustCloruroDiCalcioMash = adjustCloruroDiCalcio * mashRatioCloruroDiCalcio;
		double adjustIdrossidoDiCalcioMash = adjustIdrossidoDiCalcio * mashRatioIdrossidoDiCalcio;
		double adjustEpsomMash = adjustEpsom * mashRatioEpsom;
		double adjustBicarbonatoDiSodioMash = adjustBicarbonatoDiSodio * mashRatioBicarbonatoDiSodio;
		double adjustCloruroDiSodioMash = adjustCloruroDiSodio * mashRatioCloruroDiSodio;

		// double adjustCarbonatoDiCalcioSparge = adjustCarbonatoDiCalcio -
		// adjustCarbonatoDiCalcioMash;
		// double adjustGypsumSparge = adjustGypsum - adjustGypsumMash;
		// double adjustCloruroDiCalcioSparge = adjustCloruroDiCalcio -
		// adjustCloruroDiCalcioMash;
		// double adjustIdrossidoDiCalcioSparge = adjustIdrossidoDiCalcio -
		// adjustIdrossidoDiCalcioMash;
		// double adjustEpsomSparge = adjustEpsom - adjustEpsomMash;
		// double adjustBicarbonatoDiSodioSparge = adjustBicarbonatoDiSodio -
		// adjustBicarbonatoDiSodioMash;

		switch (type) {
		case MASH_CALCIUM:
			wp = (1 - percDistilledROMash) * waterAdjustPanel.getCalcio()
					+ (adjustCarbonatoDiCalcioMash * 105.89 + adjustGypsumMash * 60.0 + adjustCloruroDiCalcioMash * 72.0
							+ adjustIdrossidoDiCalcioMash * 143.0) / volumeMashGalloni;
			break;
		case MASH_SPARGE_CALCIUM:
			wp = (1 - percDistilledROMash) * waterAdjustPanel.getCalcio()
					+ (adjustCarbonatoDiCalcio * 105.89 + adjustGypsum * 60.0 + adjustCloruroDiCalcio * 72.0
							+ adjustIdrossidoDiCalcio * 143.0) / volumeTotaleGalloni;
			break;
		case MASH_MAGNESIUM:
			wp = (1 - percDistilledROMash) * waterAdjustPanel.getMagnesio() + (adjustEpsomMash * 24.6) / volumeMashGalloni;
			break;
		case MASH_SPARGE_MAGNESIUM:
			wp = (1 - percDistilledROMash) * waterAdjustPanel.getMagnesio() + (adjustEpsom * 24.6) / volumeTotaleGalloni;
			break;
		case MASH_SODIUM:
			wp = (1 - percDistilledROMash) * waterAdjustPanel.getSodio()
			+ (adjustCloruroDiSodioMash * 103.9 + adjustBicarbonatoDiSodioMash * 72.3) / getMashVolumeGalloni(recipe);
			break;
		case MASH_SPARGE_SODIUM:
			wp = (1 - percDistilledROMash) * waterAdjustPanel.getSodio()
					+ (adjustCloruroDiSodio * 103.9 + adjustBicarbonatoDiSodio * 72.3) / volumeTotaleGalloni;
			break;
		case MASH_CHLORIDE:
			wp = (1 - percDistilledROMash) * waterAdjustPanel.getCloruro()
			+ (adjustCloruroDiSodioMash * 160.3 + adjustCloruroDiCalcioMash * 127.47) / volumeMashGalloni;
			break;
		case MASH_SPARGE_CHLORIDE:
			wp = (1 - percDistilledROMash) * waterAdjustPanel.getCloruro()
					+ (adjustCloruroDiSodio * 160.3 + adjustCloruroDiCalcio * 127.47) / volumeTotaleGalloni;
			break;
		case MASH_SULFATE:
			wp = (1 - percDistilledROMash) * waterAdjustPanel.getSolfato()
			+ (adjustGypsumMash * 147.4 + adjustEpsomMash * 103.0) / volumeMashGalloni;
			break;
		case MASH_SPARGE_SULFATE:
			wp = (1 - percDistilledROMash) * waterAdjustPanel.getSolfato()
					+ (adjustGypsum * 147.4 + adjustEpsom * 103.0) / volumeTotaleGalloni;
			break;
		case MASH_CHLORIDE_SULFATE_RATIO:
			Double mashCloride = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_CHLORIDE);
			Double mashSulfate = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_SULFATE);
			wp = mashSulfate != 0.0 ? mashCloride / mashSulfate : Double.NaN;
			break;
		case MASH_SPARGE_CHLORIDE_SULFATE_RATIO:
			Double mashClorideTot = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_SPARGE_CHLORIDE);
			Double mashSulfateTot = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_SPARGE_SULFATE);
			wp = mashSulfateTot != 0.0 ? mashClorideTot / mashSulfateTot : Double.NaN;
			break;
		default:
			break;

		}

		return wp.isInfinite() || wp.isNaN() ? defaultValue : wp;
	}

	public static boolean isPalmerValueOk(Double value, PalmerRecommendedRangeType type) {
		boolean palmerValueOk = false;
		if (!Double.isNaN(value) && !Double.isInfinite(value)) {
			Double[] palmerRange = RicettaUtils.getPalmerRecommendedRange(type);
			palmerValueOk = palmerRange[0] <= value && value <= palmerRange[1];
		}
		return palmerValueOk;
	}

}
