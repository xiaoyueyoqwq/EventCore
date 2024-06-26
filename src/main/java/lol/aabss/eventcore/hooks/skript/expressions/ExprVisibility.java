package lol.aabss.eventcore.hooks.skript.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import lol.aabss.eventcore.events.VisibilityEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static lol.aabss.eventcore.EventCore.API;

@Name("Player Visibility State")
@Description("Gets the visibility state of a player.")
@Examples({
        "send visibility state of player",
        "set visibility state of player to off"
})
@Since("2.2")
public class ExprVisibility extends SimplePropertyExpression<Player, VisibilityEvent.VisibilityState> {

    static {
        register(ExprVisibility.class, VisibilityEvent.VisibilityState.class, "[event[core]] visibility state", "players");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "visibility";
    }

    @Override
    public @Nullable VisibilityEvent.VisibilityState convert(Player player) {
        return API.getVisibilityState(player);
    }

    @Override
    public @NotNull Class<? extends VisibilityEvent.VisibilityState> getReturnType() {
        return VisibilityEvent.VisibilityState.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            return CollectionUtils.array(VisibilityEvent.VisibilityState.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public void change(Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            if (delta[0] == null){
                return;
            }
            for (Player p : getExpr().getArray(e)){
                API.setVisibilityState(p, (VisibilityEvent.VisibilityState) delta[0]);
            }
        }
    }
}
