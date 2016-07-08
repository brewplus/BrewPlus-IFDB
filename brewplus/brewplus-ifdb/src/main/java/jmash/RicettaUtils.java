package jmash;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class RicettaUtils {

	private static Logger LOGGER = Logger.getLogger(RicettaUtils.class);

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
				Double pHFromChart = maltCategory == null || maltCategory.isCrystal()
						? 5.22 - (0.00504 * lovibond / 1000) : maltCategory.getPH();
				maltsPHfromChart.add(pHFromChart);

				Double phPesata = pHFromChart * (recipeMalt.getGrammi() / 1000);
				mediaPesataPH += phPesata;

//				LOGGER.debug(recipeMalt.getNome() + " -> " + maltCategory + " -> SRM[" + recipeMalt.getSrm() + "] °L["
//						+ lovibond + "] pH[" + pHFromChart + "] g[" + recipeMalt.getGrammi() + "] phPesata[" + phPesata
//						+ "]");
			} else {
				// SE MALTO ACIDO LO TOLGO DAL CALCOLO DEI KG TOTALI
				totalGrainWeightKg -= (recipeMalt.getGrammi() / 1000);
				totalAcidGrainWeightGr += recipeMalt.getGrammi();
			}

		}

		mediaPesataPH = mediaPesataPH / totalGrainWeightKg;
//		LOGGER.debug("pHSuMediaPesata = " + mediaPesataPH + " tot[" + totalGrainWeightKg + "]Kg tot[" + totalGrainWeightLbs + "]lbs");
		Double effectiveAlcalinity = calculateEffectiveAlcalinity(recipe);
		double residualAlcalinity = calculateResidualAlcalinity(recipe, effectiveAlcalinity);
		double mashVolumeGalloni = getMashVolumeGalloni(recipe);

		pH = mediaPesataPH + (0.1085 * mashVolumeGalloni / totalGrainWeightLbs + 0.013) * residualAlcalinity / 50;
//		LOGGER.debug(" ------> final pH[" + pH + "]");

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

			WaterNeeded waterNeeded = recipe.waterNeeded;
			WaterAdjustPanel waterAdjustPanel = recipe.waterPanel;

			double mashVolume = waterNeeded.getMashVolume();
			double mashVolumeGalloni = getMashVolumeGalloni(recipe);
			double spargeVolume = waterNeeded.getSpargeVolume();

			double mlAcidLactic = waterAdjustPanel.getLacticAcid();
			double percLacticAcidContent = waterAdjustPanel.getLacticAcidContent() / 100;
			double grCitrusAcid = waterAdjustPanel.getCitrusAcid();
			double percCitrusAcidContent = waterAdjustPanel.getCitrusAcidContent() / 100;
			double percAcidulatedMaltContent = waterAdjustPanel.getAcidulatedMaltContent() / 100.0;

			double waterCarbStart = waterAdjustPanel.getCarb();

			double adjustBicarbonatoDiSodio = waterAdjustPanel.getAdjustBicarbonatoDiSodio();
			double adjustCarbonatoDiCalcio = waterAdjustPanel.getAdjustCarbonatoDiCalcio();
			double adjustIdrossidoDiCalcio = waterAdjustPanel.getAdjustIdrossidoDiCalcio();

//			LOGGER.debug("mashVolume[" + mashVolume + "]L mashVolume[" + mashVolumeGalloni + "]gl spargeVolume["
//					+ spargeVolume + "]");

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
					+ ((adjustCarbonatoDiCalcio * 130.0)
							+ (((adjustBicarbonatoDiSodio * 157.0)
									- (176.1 * (mlAcidLactic * percLacticAcidContent
											+ grCitrusAcid * percCitrusAcidContent) * 2.0)
									- (4160.4 * acidMaltOz * percAcidulatedMaltContent * 2.5)
									+ (adjustIdrossidoDiCalcio * 357.0)) / mashVolumeGalloni));
		} catch (Exception e) {
			effectiveAlcalinity = Double.NaN;
		}
//		LOGGER.debug("effectiveAlcalinity[" + effectiveAlcalinity + "]");
		return effectiveAlcalinity;
	}

	public static double calculateMashWaterProfile(Ricetta recipe, String element) {
		WaterAdjustPanel waterAdjustPanel = recipe.waterPanel;
		double calcio = waterAdjustPanel.getCalcio();
		double magnesio = waterAdjustPanel.getMagnesio();
		double adjustCarbonatoDiCalcio = waterAdjustPanel.getAdjustCarbonatoDiCalcio();
		double adjustIdrossidoDiCalcio = waterAdjustPanel.getAdjustIdrossidoDiCalcio();
		WaterProfile waterProfile = waterAdjustPanel.getTreatment();
		double gypsum = waterProfile.getGypsum() / 1000.0;
		double calciumChloride = waterProfile.getCalciumChloride() / 1000.0;
		double epsom = waterProfile.getEpsom() / 1000.0;
		double mashVolumeGalloni = getMashVolumeGalloni(recipe);

		if ("Calcium".equals(element)) {
			return (1.0 - percDistilledROMash) * calcio + (adjustCarbonatoDiCalcio * 105.89 + gypsum * 60.0
					+ calciumChloride * 72.0 + adjustIdrossidoDiCalcio * 144.0) / mashVolumeGalloni;
		} else if ("Magnesium".equals(element)) {
			return (1.0 - percDistilledROMash) * magnesio + calciumChloride * epsom * 24.6 / mashVolumeGalloni;
		}

		return 0.0;
	}

	public static double calculateResidualAlcalinity(Ricetta recipe) {
		return calculateResidualAlcalinity(recipe, null);
	}

	public static double calculateResidualAlcalinity(Ricetta recipe, Double effectiveAlcalinity) {

		Double residualAlcalinity = Double.NaN;

		try {
			effectiveAlcalinity = effectiveAlcalinity != null && !effectiveAlcalinity.isNaN() ? effectiveAlcalinity
					: calculateEffectiveAlcalinity(recipe);
			double mashWaterProfileCalcium = calculateMashWaterProfile(recipe, "Calcium");
			double mashWaterProfileMagnesium = calculateMashWaterProfile(recipe, "Magnesium");

//			LOGGER.debug("mashWaterProfileCalcium=" + mashWaterProfileCalcium);
//			LOGGER.debug("mashWaterProfileMagnesium=" + mashWaterProfileMagnesium);

			residualAlcalinity = effectiveAlcalinity
					- ((mashWaterProfileCalcium / 1.4) + (mashWaterProfileMagnesium / 1.7));
		} catch (Exception e) {
			residualAlcalinity = Double.NaN;
		}

//		LOGGER.debug("residualAlcalinity[" + residualAlcalinity + "]");
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
		WaterNeeded waterNeeded = recipe.waterNeeded;
		return waterNeeded.getMashVolume();
	}

	public static double getMashVolumeGalloni(Ricetta recipe) {
		double mashVolume = getMashVolumeLitri(recipe);
		double mashVolumeGalloni = mashVolume / 3.785412;

		return mashVolumeGalloni;
	}
	
	public static double getSpargeVolumeLitri(Ricetta recipe) {
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

	public static Double[] getPalmerRecommendedRange(String type) {
		return PalmerRecommendedRange.rangesMap.get(type);
	}
	
	public static Double getResultingWaterProfile(Ricetta recipe, ResultingWaterProfileType type)
	{
		return getResultingWaterProfile(recipe, type, 0.0);
	}

	public static Double getResultingWaterProfile(Ricetta recipe, ResultingWaterProfileType type, Double defaultValue) {
		
		Double wp = defaultValue;
		
		WaterAdjustPanel waterAdjustPanel = recipe.waterPanel;
		double volumeMash = getMashVolumeGalloni(recipe);
		double volumeSparge = getSpargeVolumeGalloni(recipe);
		
		switch (type) {
			case MASH_CALCIUM:
				wp = (1-percDistilledROMash) * waterAdjustPanel.getCalcio() + (waterAdjustPanel.getAdjustCarbonatoDiCalcio() *105.89 + waterAdjustPanel.getAdjustGypsum() * 60.0 + waterAdjustPanel.getAdjustCloruroDiCalcio() * 72.0 + waterAdjustPanel.getAdjustIdrossidoDiCalcio() * 143.0) / getMashVolumeGalloni(recipe);
				break;
			case MASH_MAGNESIUM:
				wp = (1-percDistilledROMash) * waterAdjustPanel.getMagnesio() + (waterAdjustPanel.getAdjustEpsom() * 24.6) / getMashVolumeGalloni(recipe);
				break;
			case MASH_SODIUM:
				wp = (1-percDistilledROMash) * waterAdjustPanel.getSodio() + (waterAdjustPanel.getAdjustBicarbonatoDiSodio() * 72.3) / getMashVolumeGalloni(recipe);
				break;
			case MASH_CHLORIDE:
				wp = (1-percDistilledROMash) * waterAdjustPanel.getCloruro() + (waterAdjustPanel.getAdjustCloruroDiCalcio() * 127.47) / getMashVolumeGalloni(recipe);
				break;
			case MASH_SULFATE:
				wp = (1-percDistilledROMash) * waterAdjustPanel.getSolfato() + (waterAdjustPanel.getAdjustGypsum() * 147.4 + waterAdjustPanel.getAdjustEpsom() * 103.0) / getMashVolumeGalloni(recipe);
				break;
			case MASH_CHLORIDE_SULFATE_RATIO:
				Double mashCloride = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_CHLORIDE);
				Double mashSulfate = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_SULFATE);
				wp = mashCloride / mashSulfate;
				break;
			case MASH_SPARGE_CALCIUM:
				if (getSpargeVolumeGalloni(recipe) == 0)
				{
					wp = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_CALCIUM);
				}
				else
				{
					
				}
				break;
			case MASH_SPARGE_MAGNESIUM:
				if (getSpargeVolumeGalloni(recipe) == 0)
				{
					wp = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_MAGNESIUM);
				}
				else
				{
					
				}
				break;
			case MASH_SPARGE_SODIUM:
				if (getSpargeVolumeGalloni(recipe) == 0)
				{
					wp = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_SODIUM);
				}
				else
				{
//					wp = (1-(((percDistilledROMash*volumeMash)+(percDistilledROSparge*volumeSparge))/(volumeMash + volumeSparge)))*waterAdjustPanel.getCalcio()+((waterAdjustPanel.getAdjustCarbonatoDiCalcio()+F$46)*105.89+(waterAdjustPanel.getAdjustGypsum()+D$39)*60.0+(waterAdjustPanel.getAdjustCloruroDiCalcio()+E$39)*72.0+(waterAdjustPanel.getAdjustIdrossidoDiCalcio()+D$46)*143.0)/(volumeMash + volumeSparge);
				}
				break;
			case MASH_SPARGE_CHLORIDE:
				if (getSpargeVolumeGalloni(recipe) == 0)
				{
					wp = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_CHLORIDE);
				}
				else
				{
					
				}
				break;
			case MASH_SPARGE_SULFATE:
				if (getSpargeVolumeGalloni(recipe) == 0)
				{
					wp = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_SULFATE);
				}
				else
				{
					
				}
				break;
			case MASH_SPARGE_CHLORIDE_SULFATE_RATIO:
				if (getSpargeVolumeGalloni(recipe) == 0)
				{
					wp = getResultingWaterProfile(recipe, ResultingWaterProfileType.MASH_CHLORIDE_SULFATE_RATIO);
				}
				else
				{
					
				}
				break;
			default:
			break;
		
		}

		return wp.isInfinite() || wp.isNaN() ? defaultValue : wp;
	}

}
