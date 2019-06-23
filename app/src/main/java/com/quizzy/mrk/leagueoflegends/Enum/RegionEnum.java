package com.quizzy.mrk.leagueoflegends.Enum;

import java.util.ArrayList;

public class RegionEnum {

    private static String EUW_REGION = "EU West";
    private static String EUN_REGION = "EU Nordic and East";
    private static String BR_REGION = "Brazil";
    private static String RU_REGION = "Russia";
    private static String JP_REGION = "Japan";
    private static String OC_REGION = "Oceania";

    public static ArrayList<String> getRegionList() {
        ArrayList<String> regions = new ArrayList<>();
        regions.add(RegionEnum.EUW_REGION);
        regions.add(RegionEnum.EUN_REGION);
        regions.add(RegionEnum.BR_REGION);
        regions.add(RegionEnum.RU_REGION);
        regions.add(RegionEnum.JP_REGION);
        regions.add(RegionEnum.OC_REGION);

        return regions;
    }

    public static String getRegion(final String region) {
        String regionUrl = "";

        if (region == RegionEnum.EUW_REGION) {
            regionUrl = "euw1";
        } else if (region == RegionEnum.EUN_REGION) {
            regionUrl = "eun1";
        } else if (region == RegionEnum.BR_REGION) {
            regionUrl = "br1";
        } else if (region == RegionEnum.RU_REGION) {
            regionUrl = "ru";
        } else if (region == RegionEnum.JP_REGION) {
            regionUrl = "jp1";
        } else if (region == RegionEnum.OC_REGION) {
            regionUrl = "oc1";
        }

        return regionUrl;
    }
}
