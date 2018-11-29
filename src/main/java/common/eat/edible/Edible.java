package common.eat.edible;


import org.jetbrains.annotations.NotNull;

public interface Edible {
    @NotNull
    String getAction();

    @NotNull
    String name();

    @NotNull
    int id();
}
