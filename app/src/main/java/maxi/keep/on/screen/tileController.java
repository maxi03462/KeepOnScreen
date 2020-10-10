package maxi.keep.on.screen;

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
            ScreenControl.activarPantallaEncendida(getBaseContext());
            System.out.println("On");
        } else {
            tile.setState(tile.STATE_ACTIVE);
            ScreenControl.desactivarPantallaEncendida(getBaseContext());
            System.out.println("Off");
        }

        tile.updateTile();
    }
}
