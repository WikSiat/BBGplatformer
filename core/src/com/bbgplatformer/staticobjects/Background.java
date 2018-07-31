package com.bbgplatformer.staticobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bbgplatformer.entities.Player;

public class Background extends Image{
	
	private final static int BG1_STARTING_X = -370;
	private final static int BG2_STARTING_X = 1230;
	private final static int BG_STARTING_Y = -130;
	
	public static int bgIndex;
	
	private final static String BG_IMAGE = "tlo.jpg";
	
	public Background() {
		super(new Texture(BG_IMAGE));
	}
	
	public static void setStartingBackgroundPosition(Background bg1, Background bg2) {
		bgIndex = 1;
		bg1.setPosition(Background.BG1_STARTING_X,BG_STARTING_Y);
		bg2.setPosition(Background.BG2_STARTING_X,BG_STARTING_Y);
	}
	
	// renders looping background that can scroll infinitely and will adjust to player's moves
	public static void bgUpdate(Player player,Background bg1, Background bg2) {
			if (player.getX() == (BG2_STARTING_X + bgIndex * 1600 - 430) && player.isTurnedRight) {
				switch (bgIndex % 2) {
				case 0:
					bg2.setX(BG2_STARTING_X + bgIndex * 1600);
					break;
				case 1:
					bg1.setX(BG2_STARTING_X + bgIndex * 1600);
					break;
				default:
					break;
				}
				bgIndex++;
			} else if (player.getX() == (BG2_STARTING_X + (bgIndex - 1) * 1600 - 430) && !player.isTurnedRight) {
				bgIndex--;
				if (bgIndex == 0) {
					setStartingBackgroundPosition(bg1,bg2);
				} else {
					switch (bgIndex % 2) {
					case 0:
						bg2.setX(BG1_STARTING_X + (bgIndex - 1) * 1600);
						break;
					case 1:
						bg1.setX(BG1_STARTING_X + (bgIndex - 1) * 1600);
						break;
					default:
						break;
					}
				}
			}
		}
}
