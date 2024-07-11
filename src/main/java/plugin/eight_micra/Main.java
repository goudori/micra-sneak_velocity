package plugin.eight_micra;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class Main extends JavaPlugin implements Listener {

    private int count;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    // プレイヤーがマイクラサーバーに参加した時のイベント
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        // ベッドアイテム一個追加
        ItemStack bedItem = new ItemStack(Material.YELLOW_BED, 1);
        player.getInventory().addItem(bedItem);
    }

    /**
     * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
     *
     * @param e イベント
     *
     * velocityで、プレイヤーを飛ばし続けるとサーバーエラーになる。
     */
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
        // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
        Player player = e.getPlayer();
        World world = player.getWorld();

        if (count % 2 == 0) {
//            プレイヤーを飛ばすため、velocityを設定
            Vector velocity = player.getVelocity();
//            上方向に設定
           velocity.setY(1);

            player.setVelocity(velocity);

            // ベッドブロックをプレイヤーのロケーション地点に対して出現させる。
            FallingBlock bed = world.spawnFallingBlock(player.getLocation(), Material.YELLOW_BED.createBlockData());

            // ブロックがアイテム化しないように設定。
            bed.setDropItem(false);
        }
        count++;
    }


}
