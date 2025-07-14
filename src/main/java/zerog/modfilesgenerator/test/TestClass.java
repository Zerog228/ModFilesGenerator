package zerog.modfilesgenerator.test;

import zerog.modfilesgenerator.annotations.WIP;
import zerog.modfilesgenerator.enums.ProgramVersion;
import zerog.modfilesgenerator.enums.WorkMode;

@WIP(version = ProgramVersion.V1_19_2_GECKO_3, workmode = {WorkMode.TEST, WorkMode.ALL_SIDES}, message = "WIP mazafaka")
public class TestClass {
}
