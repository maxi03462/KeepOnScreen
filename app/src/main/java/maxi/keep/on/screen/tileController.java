package maxi.keep.on.screen;

import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class tileController extends TileService {
    @Override
    public void onClick() {
        super.onClick();

        Tile tile = getQsTile();
        boolean isActive = (tile.getState() == tile.STATE_ACTIVE);

        if (isActive) {
            tile.setState(tile.STATE_INACTIVE);
            ScreenControl.desactivarPantallaEncendida(getBaseContext());
            tile.setIcon(Icon.createWithResource(this, R.drawable.ic_tile_off));
            System.out.println("Off");
        } else {
            tile.setState(tile.STATE_ACTIVE);
            ScreenControl.activarPantallaEncendida(getBaseContext());
            tile.setIcon(Icon.createWithResource(this, R.drawable.ic_tile_on));
            System.out.println("On");
        }

        tile.updateTile();
    }

    public void offTile() {
        Tile tile = getQsTile();

        tile.setState(tile.STATE_INACTIVE);
        tile.setIcon(Icon.createWithResource(this, R.drawable.ic_tile_off));

        tile.updateTile();
    }
}
