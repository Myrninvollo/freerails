package jfreerails.parsers;

import java.util.HashSet;

final public class TrackRuleImplConstructorParameters {
		
		public boolean enableDoubleTrack=false;

		public boolean station=false;

		public String[][] legalRoutesAcrossNodeTemplates=null;
		
		public HashSet canOnlyBuildOnTheseTerrainTypes=null;

		public int ruleNumber=0;

		public String[] legalTrackTemplates = new String[0];

		public int maximumConsecutivePieces=-1;

		public HashSet cannotBuildOnTheseTerrainTypes=new HashSet();

		public int rGBvalue=0;

		public String typeName="default track rule";

		public boolean signalTower=false;

	}
