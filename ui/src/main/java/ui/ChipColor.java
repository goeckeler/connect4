package ui;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Map;

import com.google.common.collect.Maps;

import game.Player;

public class ChipColor
{
  static Map<String, Character> colors = Maps.newHashMap();

  public static void useColorFor(final String name, final Character character) {
    colors.put(name, character);
  }

  public static Character colorFor(final String name) {
    Character character = colors.get(name);

    if (character == null) {
      character = colors.size() % 2 == 0 ? 'X' : 'O';
      colors.put(name, character);
    }

    return character;
  }

  public static Character colorFor(final Player player) {
    final String name = isNullOrEmpty(player.getName()) ? Integer.toString(player.hashCode()) : player.getName();
    return colorFor(name);
  }
}
