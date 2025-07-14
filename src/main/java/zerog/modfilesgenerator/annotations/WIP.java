package zerog.modfilesgenerator.annotations;

import zerog.modfilesgenerator.enums.ProgramVersion;
import zerog.modfilesgenerator.enums.WorkMode;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(WIPContainer.class)
public @interface WIP {
    ProgramVersion version();
    String message() default "Work in progress!";
    WorkMode[] workmode();
}


