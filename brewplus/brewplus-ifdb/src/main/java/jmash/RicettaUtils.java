package jmash;

import java.util.ArrayList;
import java.util.List;

public class RicettaUtils {
	
	public static final double percDistilledROMash = 0.0;
	public static final double percDistilledROSparge = 0.0;
	public static final double percLacticAcidContent = 88 / 100;
	public static final double mlAcidLactic = 0;
	public static final double percAcidulatedMaltContent = 2 / 100;
	
	public static final double adjustIdrossidoDiCalcio = 0;

	public static Double calculatePH(Ricetta recipe) {
		Double pH = Double.NaN;
		List<Malt> recipeMalts = recipe.maltTableModel.getRows();

		List<Double> maltsPHfromChart = new ArrayList<Double>();

		Double mediaPesataPH = 0.0;
		
		double totalGrainWeightKg = getTotalGrainWeightKg(recipe);
		double totalGrainWeightLbs = getTotalGrainWeighLbs(recipe);

		for (Malt recipeMalt : recipeMalts) {
			if (!recipeMalt.getNome().toUpperCase().startsWith("ACID"))
			{
				MaltCategory maltCategory = findMaltCategory(recipeMalt);
				Double lovibond = (recipeMalt.getSrm() + 0.6) / 1.3546;
				Double pHFromChart = maltCategory == null ? 5.22 - (0.00504 * lovibond / 1000) : maltCategory.getPH();
				maltsPHfromChart.add(pHFromChart);
	
				Double phPesata = pHFromChart * (recipeMalt.getGrammi() / 1000);
				mediaPesataPH += phPesata;
				
				System.out.println(
						recipeMalt.getNome() + " -> " + maltCategory + " -> SRM[" + recipeMalt.getSrm() + "] °L[" + lovibond
								+ "] pH[" + pHFromChart + "] g[" + recipeMalt.getGrammi() + "] phPesata[" + phPesata + "]");
			}
		}

		mediaPesataPH = mediaPesataPH / totalGrainWeightKg;
		System.out.println("media pesata pH = " + mediaPesataPH + " totGrammi[" + recipe.maltTableModel.getTotGrammi() + "]");

		double residualAlcalinity = calculateResidualAlcalinity(recipe);
		double mashVolumeGalloni = getMashVolumeGalloni(recipe);
		
		pH = mediaPesataPH + (0.1085* mashVolumeGalloni / totalGrainWeightLbs + 0.013) * residualAlcalinity / 50;

		return pH;
	}
	
	public static double getTotalGrainWeightKg(Ricetta recipe)
	{
		return recipe.maltTableModel.getTotGrammi() / 1000;
	}
	
	public static double getTotalGrainWeighLbs(Ricetta recipe)
	{
		return getTotalGrainWeightKg(recipe) * 2.20462;
	}

	public static double calculateEffectiveAlcalinity(Ricetta recipe) {
		
		Double effectiveAlcalinity = 0.0;

		WaterNeeded waterNeeded = recipe.waterNeeded;
		double mashVolume = waterNeeded.getMashVolume();
		double mashVolumeGalloni = getMashVolumeGalloni(recipe);
		double spargeVolume = waterNeeded.getSpargeVolume();

		WaterAdjustPanel waterAdjustPanel = recipe.waterPanel;
		double carb = waterAdjustPanel.getCarb();
		
		double adjustBicarbonatoDiSodio = waterAdjustPanel.getAdjustBicarbonatoDiSodio();
		double adjustCarbonatoDiCalcio = waterAdjustPanel.getAdjustCarbonatoDiCalcio();

		System.out.println("mashVolume[" + mashVolume + "] spargeVolume[" + spargeVolume + "]");
		
		List<Malt> recipeMalts = recipe.maltTableModel.getRows();

		double acidMaltGramms = 0.0;
		double acidMaltOz = 0.0;
	
		for (Malt recipeMalt : recipeMalts) {
			if (recipeMalt.getNome().toUpperCase().startsWith("ACID"))
			{
				acidMaltGramms *= recipeMalt.getGrammi();
			}
		}
		acidMaltOz = acidMaltGramms / 28.34952;

		effectiveAlcalinity = ((1 - percDistilledROMash) * carb * (50/61)) + ((adjustCarbonatoDiCalcio*130) + (((adjustBicarbonatoDiSodio * 157) - (176.1 * mlAcidLactic * percLacticAcidContent * 2) - (4160.4 * acidMaltOz * percAcidulatedMaltContent * 2.5) +  (adjustIdrossidoDiCalcio * 357)) / mashVolumeGalloni));

		System.out.println("effectiveAlcalinity[" + effectiveAlcalinity + "]");
		return effectiveAlcalinity;
	}
	
	public static double calculateMashWaterProfile(Ricetta recipe, String element)
	{
		WaterAdjustPanel waterAdjustPanel = recipe.waterPanel;
		double calcio = waterAdjustPanel.getCalcio();
		double magnesio = waterAdjustPanel.getMagnesio();
		double adjustCarbonatoDiCalcio = waterAdjustPanel.getAdjustCarbonatoDiCalcio();
		WaterProfile waterProfile = waterAdjustPanel.getTreatment();
		double gypsum = waterProfile.getGypsum();
		double calciumChloride = waterProfile.getCalciumChloride();
		double epsom = waterProfile.getEpsom();
		double mashVolumeGalloni = getMashVolumeGalloni(recipe);
		
		if ("Calcium".equals(element))
		{
			return (1 - percDistilledROMash) * calcio + (adjustCarbonatoDiCalcio * 105.89 + gypsum * 60 + calciumChloride * 72 + adjustIdrossidoDiCalcio * 144) / mashVolumeGalloni;
		}
		else if ("Magnesium".equals(element))
		{
			return (1 - percDistilledROMash) * magnesio + calciumChloride * epsom * 24.6 / mashVolumeGalloni;
		}
		
		return 0.0;
	}
	
	public static double calculateResidualAlcalinity(Ricetta recipe) {
		Double residualAlcalinity = 0.0;
		
		double effectiveAlcalinity = calculateEffectiveAlcalinity(recipe);
		double mashWaterProfileCalcium = calculateMashWaterProfile(recipe, "Calcium");
		double mashWaterProfileMagnesium = calculateMashWaterProfile(recipe, "Magnesium");
						
		residualAlcalinity = effectiveAlcalinity - ((mashWaterProfileCalcium / 1.4) + (mashWaterProfileMagnesium / 1.7));
		
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
	
	public static double getMashVolumeGalloni(Ricetta recipe)
	{
		WaterNeeded waterNeeded = recipe.waterNeeded;
		double mashVolume = waterNeeded.getMashVolume();
		double mashVolumeGalloni = mashVolume /3.785412;
		
		return mashVolumeGalloni;
	}

	public static MaltCategory findMaltCategory(MaltType maltType) {
		MaltCategory maltCategory = null;
		if (maltType != null) {
			List<MaltCategory> maltCategories = Gui.maltCategoryPickerTableModel.getRows();

			String maltTypeName = maltType.getNome();

			for (MaltCategory tmpMaltCategory : maltCategories) {
				if (maltTypeName.equals(tmpMaltCategory.getNome())) {
					maltCategory = tmpMaltCategory;
					break;
				}
			}
		}
		return maltCategory;
	}

	public static MaltCategory findMaltCategory(Malt malt) {
		MaltType maltType = findMaltType(malt);
		return findMaltCategory(maltType);
	}
}
