package zerog.modfilesgenerator.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WIPContainer {
    WIP[] value();
}
