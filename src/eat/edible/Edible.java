package eat.edible;

import com.sun.istack.internal.NotNull;

public interface Edible {
    @NotNull
    String getAction();
    @NotNull
    int hitPointValue();
    @NotNull
    String name();
    @NotNull
    int id();
}
