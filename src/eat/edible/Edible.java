package eat.edible;

import com.sun.istack.internal.NotNull;

public interface Edible {
    @NotNull
    String getAction();

    @NotNull
    String name();

    @NotNull
    int id();
}
