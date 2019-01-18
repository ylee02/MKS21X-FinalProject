//API : http://mabe02.github.io/lanterna/apidocs/2.1/
import java.util.Random;
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
import java.util.ArrayList;

public class Game {

	private static int stairX;
	private static int stairY;

	public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}

	public static int setRoom(int x, int y, Terminal terminal, int columns, int rows, int seed, ArrayList<Integer> enemiesArrayList){
		enemiesArrayList.clear();
		Random randgen;
		int tempseed = (int)(Math.random()*10000);
		if (seed != -1){
			randgen = new Random(seed);
		}else{
			randgen = new Random(tempseed);
		}
		int enemies = randgen.nextInt(4) + 2;
		for (int i = 0; i < rows; i++){
			x = 0;
			terminal.moveCursor(x,y);
			for (int r= 0; r < columns; r++){
					terminal.moveCursor(x,y);
					if (x == 0 || x== columns - 1){
						terminal.applyForegroundColor(Terminal.Color.GREEN);
						terminal.putCharacter('|');
					}else{
						if (y == 0 || y == rows - 1){
								terminal.applyForegroundColor(Terminal.Color.GREEN);
								terminal.putCharacter('-');
						}else{
								terminal.applyForegroundColor(Terminal.Color.WHITE);
								terminal.putCharacter('.');
						}
					}
					x = x + 1;
			}
			y = y + 1;
		}
		terminal.moveCursor(columns / 2, 0);
		terminal.putCharacter(' ');
		terminal.moveCursor(columns / 2 - 1, 0);
		terminal.putCharacter(' ');
		terminal.moveCursor(columns / 2 - 1, rows - 1);
		terminal.putCharacter(' ');
		terminal.moveCursor(columns / 2, rows - 1);
		terminal.putCharacter(' ');
		terminal.moveCursor(columns, rows / 2);
		terminal.putCharacter(' ');
		terminal.moveCursor(columns, rows / 2 - 1);
		terminal.putCharacter(' ');
		terminal.moveCursor(0, rows / 2);
		terminal.putCharacter(' ');
		terminal.moveCursor(0, rows / 2 - 1);
		terminal.putCharacter(' ');
		while (enemies > 0){
			int enemyx = randgen.nextInt(columns - 2) + 1;
			int enemyy = randgen.nextInt(rows - 2) + 1;
			terminal.moveCursor(enemyx,enemyy);
			int whichguy = randgen.nextInt(5);
			//Make "ABCDE" eventually: more enemies
			terminal.putCharacter("AAAAA".charAt(whichguy));
			enemiesArrayList.add(enemyx);
			enemiesArrayList.add(enemyy);
			enemies--;
		}
		if (randgen.nextInt() % 10 == 0){
			stairX = randgen.nextInt(columns - 2) + 1;
			stairY = randgen.nextInt(rows - 2) + 1;
			terminal.moveCursor(stairX, stairY);
			terminal.putCharacter('+');
		}
		return tempseed;
	}

	public static void resetStair() {
		stairX = -1;
		stairY = -1;
	}

	public static void main(String[] args) {

		Togue game = new Togue();
		Player player = game.getPlayer();
		Monster monster = new Monster("Goblin", 0, 0);

		//used to calculate staircase/boss generation, switching screens, etc.
		int roomsBeenIn;
		Random randgen = new Random();

		//direction of the last room (for seeds)
		String lastRoom = "";

		int mode = 0;

		int x = 0;
		int y = 0;

		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

		boolean running = true;

		long tStart = System.currentTimeMillis();
		long lastSecond = 0;

		TerminalSize terminalsize = terminal.getTerminalSize();

		int rows = terminalsize.getRows() - 3;
		String rowsString = "" + rows;
		int columns = terminalsize.getColumns();
		terminal.moveCursor(x,y);

		ArrayList<Integer> enemiesal = new ArrayList<>();
		//INITIAL ROOM GENERATION
		int oldseed = setRoom(x,y,terminal, columns, rows, -1, enemiesal);
		//CALL EACH NEW SETROOM WITH OLDSEED AS THE SEED TO GO BACKWARDS
		int oldest = oldseed;

		boolean firsttime = false;
		boolean firsttimeagain = false;

		terminal.moveCursor(columns / 2, rows / 2);
		terminal.putCharacter('@');
		x = columns / 2;
		y = rows / 2;

		int ik = 0;
		boolean temp = true;
		boolean attackanymore = true;

		while (running){
		Key key = terminal.readInput();
		if (mode == 2){
			for (int i = 0;i < columns; i++){
				for (int c = 0; c < terminalsize.getRows(); c++){
					terminal.moveCursor(i,c);
					terminal.putCharacter(' ');
				}
			}
			putString(columns / 2, rows / 2, terminal, "You Died!");
			putString(columns / 2, 1 + (rows / 2), terminal, "Press Escape to exit!");
			if (key != null){
				if (key.getKind() == Key.Kind.Escape) {
					terminal.exitPrivateMode();
					running = false;
				}
			}
		}
		if (mode == 1){
			if (firsttimeagain){
				monster = new Monster("Goblin", 0, 0);
				firsttimeagain = false;
			}
			if (temp){
				long timeR = System.currentTimeMillis();
				int i = 0;
				while (i < columns) {
					if ((System.currentTimeMillis() - timeR) % 100 == 0) {
						for (int c = 0; c < terminalsize.getRows(); c++){
							terminal.moveCursor(i,c);
							if (randgen.nextInt() % 8 == 0) {
								terminal.putCharacter(' ');
							}
							else {
								terminal.putCharacter('-');
							}
						}
						i++;
					}
				}
				i = 0;
				while (i < columns) {
					if ((System.currentTimeMillis() - timeR) % 100 == 0) {
						for (int c = 0; c < terminalsize.getRows(); c++){
							terminal.moveCursor(i,c);
							terminal.putCharacter(' ');
						}
						i++;
					}
				}
				temp = false;
			}

			terminal.moveCursor(columns / 3, rows / 3);
			terminal.applyForegroundColor(Terminal.Color.GREEN);
			terminal.putCharacter('@');
			putString(columns / 3, (rows / 3) + 1, terminal, "Attack: "  + player.getStrength());
			putString(columns / 3, (rows / 3) + 2, terminal, "Health: "  + player.getHealth());

			terminal.moveCursor(2 * columns / 3, rows / 3);
			terminal.applyForegroundColor(Terminal.Color.GREEN);
			terminal.putCharacter('A');
			putString(2 * columns / 3, (rows / 3) + 1, terminal, "Attack: "  + monster.getStrength());
			putString(2 * columns / 3, (rows / 3) + 2, terminal, "Health: "  + monster.getHealth());

			terminal.applyForegroundColor(Terminal.Color.WHITE);
			if (attackanymore){
				putString(0,2 * rows / 3, terminal, "Press 1 for Rock, Press 2 for Paper, Press 3 for Scissors");
			}
			String status = "";
			if (key != null){
				//debugging purposes only
				if (key.getCharacter() == '4') {
					mode = 0;
					x++;
					firsttime = true;
				}
				int enemyattack = 0;

				if (key.getCharacter() == '1'){
					if (attackanymore){
						enemyattack = (int)(Math.random()*3) + 1;
						putString(0, rows - 5, terminal, "                                                                                           ");
						if (enemyattack == 1){
							status = "Enemy played rock. Nothing happened.1";
						}
						if (enemyattack == 2){
							status = "Enemy played paper. They attacked you.2";
						}
						if (enemyattack == 3){
							status = "Enemy played scissors. You attacked them.3";
						}
					}
				}
				if (key.getCharacter() == '2'){
					if (attackanymore){
						enemyattack = (int)(Math.random()*3) + 1;
						putString(0, rows - 5, terminal, "                                                                                           ");
						if (enemyattack == 2){
							status = "Enemy played paper. Nothing happened.1";
						}
						if (enemyattack == 3){
							status = "Enemy played scissors. They attacked you.2";
						}
						if (enemyattack == 1){
							status = "Enemy played rock. You attacked them.3";
						}
					}
				}
				if (key.getCharacter() == '3'){
					if (attackanymore){
						putString(0, rows - 5, terminal, "                                                                                           ");
						enemyattack = (int)(Math.random()*3) + 1;
						if (enemyattack == 3){
							status = "Enemy played scissors. Nothing happened.1";
						}
						if (enemyattack == 1){
							status = "Enemy played rock. They attacked you.2";
						}
						if (enemyattack == 2){
							status = "Enemy played paper. You attacked them.3";
						}
					}
				}
			}
			if (!status.equals("")){
				putString(0, rows - 5, terminal, status.substring(0, status.length() - 1));
				if (!(status.charAt(status.length() - 1) == '1')){
					//putString(0, rows, terminal, "Press 4 to exit");
				}
				if (status.charAt(status.length() - 1) == '2'){
					player.setHealth(player.getHealth() - monster.getStrength());
					putString(columns / 3, (rows / 3) + 1, terminal, "Attack: "  + player.getStrength());
					putString(columns / 3, (rows / 3) + 2, terminal, "Health: "  + player.getHealth());
				}
				if (status.charAt(status.length() - 1) == '3'){
					monster.setHealth(monster.getHealth() - player.getStrength());
					putString(2 * columns / 3, (rows / 3) + 1, terminal, "Attack: " + monster.getStrength());
					//MAKE THIS UPDATE
					putString(2 * columns / 3, (rows / 3) + 2, terminal, "Health: " + monster.getHealth());
					putString(2 * columns / 3, (rows / 3) + 2, terminal, "Health: " + monster.getHealth());
				}
			}
			if (player.getHealth() <= 0){
				mode = 2;
			}
			if (monster.getHealth() <= 0){
				attackanymore = false;
				putString(0,2 * rows / 3, terminal, "                                                                                           ");
				putString(0, rows - 5, terminal, "                                                                                           ");
				putString(0, rows, terminal, "You killed the enemy! Press 4 to exit to map.");
			}
		}
		if (mode == 0){
			if (firsttime){
				//TODO: get rid of "phantom" enemy
				setRoom(0,0,terminal, columns, rows, oldest, enemiesal);
				terminal.moveCursor(x,y);
				terminal.putCharacter('@');
				firsttime = false;
				terminal.moveCursor(enemiesal.get(ik),enemiesal.get(ik + 1));
				terminal.applyForegroundColor(Terminal.Color.WHITE);
				terminal.putCharacter('.');
				enemiesal.remove(ik);
				enemiesal.remove(ik);
				//x--;
				//terminal.moveCursor(x,y);
				//terminal.putCharacter('@');
			}
			if (key == null){
				for (int i = 0; i < enemiesal.size(); i += 2){
					if (x == enemiesal.get(i)){
						if (y == enemiesal.get(i + 1)){
							mode = 1;
							firsttimeagain = true;
							attackanymore = true;
							ik = i;
							temp = true;
							i = enemiesal.size();
						}
					}
				}
			}
			if (key != null){
				if (key.getKind() == Key.Kind.Escape) {
					terminal.exitPrivateMode();
					running = false;
				}
				//USE 26x11 terminal size
				if (key.getKind() == Key.Kind.ArrowRight) {
					if (x == columns - 2 && ((y == rows / 2) || (y == rows / 2 - 1))){
						resetStair();
						if (lastRoom.equals("right")){
							setRoom(0,0,terminal, columns, rows, oldseed, enemiesal);
							oldseed = oldest;
						}else{
							oldest = setRoom(0,0,terminal, columns, rows, -1, enemiesal);
						}
						x = 0;
						terminal.moveCursor(x,y);
						terminal.applyForegroundColor(Terminal.Color.GREEN);
						terminal.putCharacter('@');
						lastRoom = "left";
					}
					if (x != columns - 2 && x != columns - 1 && x != columns){
						if (x + 1 == stairX && y == stairY) {
							game.setFloor(game.getFloor() + 1);
							setRoom(0,0,terminal, columns, rows, - 1, enemiesal);
							x = (columns / 2);
							y = (rows / 2);
							terminal.moveCursor(x,y);
							terminal.applyForegroundColor(Terminal.Color.GREEN);
							terminal.putCharacter('@');
						}
						terminal.applyForegroundColor(Terminal.Color.WHITE);
						terminal.moveCursor(x,y);
						terminal.putCharacter('.');
						terminal.moveCursor(x + 1,y);
						terminal.applyForegroundColor(Terminal.Color.GREEN);
						terminal.putCharacter('@');
						x++;
					}
					terminal.moveCursor(0,rows / 2);
					terminal.applyForegroundColor(Terminal.Color.WHITE);
					terminal.putCharacter(' ');
					terminal.moveCursor(0,rows / 2 - 1);
					terminal.applyForegroundColor(Terminal.Color.WHITE);
					terminal.putCharacter(' ');
				}
				if (key.getKind() == Key.Kind.ArrowLeft) {
					if (x == 1 && ((y == rows / 2) || (y == rows / 2 - 1))){
						if (lastRoom.equals("left")){
							resetStair();
							setRoom(0,0,terminal, columns, rows, oldseed, enemiesal);
							oldseed = oldest;
						}else{
							oldest = setRoom(0,0,terminal, columns, rows, -1, enemiesal);
						}
						x = columns - 1;
						terminal.moveCursor(x,y);
						terminal.applyForegroundColor(Terminal.Color.GREEN);
						terminal.putCharacter('@');
						lastRoom = "right";
					}
					if (x != 1){
						if (x - 1 == stairX && y == stairY) {
							game.setFloor(game.getFloor() + 1);
							setRoom(0,0,terminal, columns, rows, - 1, enemiesal);
							x = (columns / 2);
							y = (rows / 2);
							terminal.moveCursor(x,y);
							terminal.applyForegroundColor(Terminal.Color.GREEN);
							terminal.putCharacter('@');
						}
						terminal.applyForegroundColor(Terminal.Color.WHITE);
						terminal.moveCursor(x,y);
						terminal.putCharacter('.');
						terminal.moveCursor(x - 1,y);
						terminal.applyForegroundColor(Terminal.Color.GREEN);
						terminal.putCharacter('@');
						x--;
					}
					terminal.moveCursor(columns, rows / 2);
					terminal.putCharacter(' ');
					terminal.moveCursor(columns, rows / 2 - 1);
					terminal.putCharacter(' ');
				}
				if (key.getKind() == Key.Kind.ArrowUp) {
					if (((x == columns / 2) || (x == columns / 2 - 1)) && y == 1){
						resetStair();
						if (lastRoom.equals("up")){
							setRoom(0,0,terminal, columns, rows, oldseed, enemiesal);
							oldseed = oldest;
						}else{
							oldest = setRoom(0,0,terminal, columns, rows, -1, enemiesal);
						}
						y = rows - 1;
						terminal.moveCursor(x,y);
						terminal.applyForegroundColor(Terminal.Color.GREEN);
						terminal.putCharacter('@');
						lastRoom = "down";
					}
					if (y != 1){
						if (x == stairX && y - 1 == stairY) {
							game.setFloor(game.getFloor() + 1);
							setRoom(0,0,terminal, columns, rows, - 1, enemiesal);
							x = (columns / 2);
							y = (rows / 2);
							terminal.moveCursor(x,y);
							terminal.applyForegroundColor(Terminal.Color.GREEN);
							terminal.putCharacter('@');
						}
						terminal.applyForegroundColor(Terminal.Color.WHITE);
						terminal.moveCursor(x,y);
						terminal.putCharacter('.');
						terminal.moveCursor(x,y - 1);
						terminal.applyForegroundColor(Terminal.Color.GREEN);
						terminal.putCharacter('@');
						y--;
					}
					terminal.moveCursor(columns / 2 - 1, rows - 1);
					terminal.putCharacter(' ');
					terminal.moveCursor(columns / 2, rows - 1);
					terminal.putCharacter(' ');
				}
				if (key.getKind() == Key.Kind.ArrowDown) {
					if (((x == columns / 2) || (x == columns / 2 - 1)) && y == rows - 2){
						resetStair();
						if (lastRoom.equals("down")){
							setRoom(0,0,terminal, columns, rows, oldseed, enemiesal);
							oldseed = oldest;
						}else{
							oldest = setRoom(0,0,terminal, columns, rows, -1, enemiesal);
						}
						y = 0;
						terminal.moveCursor(x,y);
						terminal.applyForegroundColor(Terminal.Color.GREEN);
						terminal.putCharacter('@');
						lastRoom = "up";
					}
					if (y != rows - 2){
						if (x == stairX && y + 1 == stairY) {
							game.setFloor(game.getFloor() + 1);
							setRoom(0,0,terminal, columns, rows, - 1, enemiesal);
							x = (columns / 2);
							y = (rows / 2);
							terminal.moveCursor(x,y);
							terminal.applyForegroundColor(Terminal.Color.GREEN);
							terminal.putCharacter('@');
						}
						terminal.applyForegroundColor(Terminal.Color.WHITE);
						terminal.moveCursor(x,y);
						terminal.putCharacter('.');
						terminal.moveCursor(x,y + 1);
						terminal.applyForegroundColor(Terminal.Color.GREEN);
						terminal.putCharacter('@');
						y++;
					}
					terminal.moveCursor(columns / 2, 0);
					terminal.putCharacter(' ');
					terminal.moveCursor(columns / 2 - 1, 0);
					terminal.putCharacter(' ');
				}
			}
			terminal.applyForegroundColor(Terminal.Color.WHITE);
			putString(0,rows, terminal, "Floor: " + game.getFloor() + "     Level: " + player.getLevel() + "     HP: " + player.getHealth() + "     Str: " + player.getStrength() + "     Luck: " + player.getLuck() + "     Armor: " + player.getCons());
		}
	}

/*
		while(running){

			terminal.moveCursor(x,y);
			terminal.applyBackgroundColor(Terminal.Color.WHITE);
			terminal.applyForegroundColor(Terminal.Color.BLACK);
			//applySGR(a,b) for multiple modifiers (bold,blink) etc.
			terminal.applySGR(Terminal.SGR.ENTER_UNDERLINE);
			terminal.putCharacter('\u00a4');
			//terminal.putCharacter(' ');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			terminal.applySGR(Terminal.SGR.RESET_ALL);


			terminal.moveCursor(size.getColumns()-5,5);
			terminal.applyBackgroundColor(Terminal.Color.RED);
			terminal.applyForegroundColor(Terminal.Color.YELLOW);
			terminal.applySGR(Terminal.SGR.ENTER_BOLD);
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.putCharacter('\u262d');
			terminal.putCharacter(' ');
			terminal.moveCursor(size.getColumns()-5,6);
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);

			Key key = terminal.readInput();

			if (key != null)
			{

				if (key.getKind() == Key.Kind.Escape) {

					terminal.exitPrivateMode();
					running = false;
				}

				if (key.getKind() == Key.Kind.ArrowLeft) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					x--;
				}

				if (key.getKind() == Key.Kind.ArrowRight) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					x++;
				}

				if (key.getKind() == Key.Kind.ArrowUp) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					y--;
				}

				if (key.getKind() == Key.Kind.ArrowDown) {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					y++;
				}
				//space moves it diagonally
				if (key.getCharacter() == ' ') {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					y++;
					x++;
				}
				putString(1,4,terminal,"["+key.getCharacter() +"]");
				putString(1,1,terminal,key+"        ");//to clear leftover letters pad withspaces
			}

			//DO EVEN WHEN NO KEY PRESSED:
			long tEnd = System.currentTimeMillis();
			long millis = tEnd - tStart;
			putString(1,2,terminal,"Milliseconds since start of program: "+millis);
			if(millis/1000 > lastSecond){
				lastSecond = millis / 1000;
				//one second has passed.
				putString(1,3,terminal,"Seconds since start of program: "+lastSecond);

			}


		}*/
	}
}