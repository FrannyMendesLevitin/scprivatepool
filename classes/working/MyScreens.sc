MyScreens : Object {

	classvar result;

	*resolution {
		(result == nil).if{ this.refresh };
		^result;
	}
	
	*numScreens {
		^this.resolution.size;
	}
	
	*refresh {
		�var systemProfiler;
		�systemProfiler = "system_profiler SPDisplaysDataType | grep Resolution".unixCmdGetStdOut;
		�result = systemProfiler
			� .findRegexp("(?<!@ )[0-9]{3,}")
			� .collect({|item| item[1].asInteger})
			� .clump(2);
		�^result;
	}
}