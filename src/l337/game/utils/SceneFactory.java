package l337.game.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import l337.game.SceneBasedGame;
import l337.game.scene.Scene;

public final class SceneFactory {
	private static Map<String, Scene> sceneCache = new HashMap<>();
	
	
	public static Scene get(String endScene, SceneBasedGame game) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		Scene s = sceneCache.get(endScene);
		if (s == null) {
			// cache miss
			s = create(endScene, game);
			sceneCache.put(endScene, s);
		}
		return s;
	}
	
	public static Scene create(String sceneClass, SceneBasedGame game) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		Scene s = null;
		Class<?> clazz = Class.forName(sceneClass);
		Constructor<?> constructor = clazz.getConstructor(SceneBasedGame.class);
		s = (Scene) constructor.newInstance(game);
		return s;
	}
}
