//API : http://mabe02.github.io/lanterna/apidocs/2.1/
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


public class TerminalDemo {

	public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}

	public static void setRoom(int x, int y, Terminal terminal, int columns, int rows){
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
		terminal.moveCursor(columns / 2 - 1, rows);
		terminal.putCharacter(' ');
		terminal.moveCursor(columns / 2, rows);
		terminal.putCharacter(' ');
		terminal.moveCursor(columns, rows / 2);
		terminal.putCharacter(' ');
		terminal.moveCursor(columns, rows / 2 - 1);
		terminal.putCharacter(' ');
		terminal.moveCursor(0, rows / 2);
		terminal.putCharacter(' ');
		terminal.moveCursor(0, rows / 2 - 1);
		terminal.putCharacter(' ');

		terminal.moveCursor(1, 1);
		x = 1;
		y = 1;
		terminal.putCharacter('@');
	}

	public static void main(String[] args) {


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
		//terminalsize.setColumns(10);
		//terminalsize.setRows(10);
		int rows = terminalsize.getRows();
		String rowsString = "" + rows;
    int columns = terminalsize.getColumns();
    terminal.moveCursor(x,y);
    /*for (int i = 0; i < columns; i++){
			x = x + 1;
			terminal.moveCursor(x,y);
			terminal.putCharacter('-');
    }
		for (int i = 0; i < rows; i++){
			y = y + 1;
			terminal.moveCursor(x,y);
      terminal.putCharacter('|');
    }
		for (int i = 0; i < columns; i++){
      x = x - 1;
			terminal.moveCursor(x,y);
      terminal.putCharacter('-');
    }
		for (int i = 0; i < rows - 1; i++){
      y = y - 1;
			terminal.moveCursor(x,y);
      terminal.putCharacter('|');
    }*/
		setRoom(x,y,terminal, columns, rows);
		x = 1;
		y = 1;
		while (running){
      	Key key = terminal.readInput();
		if (key != null){
        	if (key.getKind() == Key.Kind.Escape) {
        		terminal.exitPrivateMode();
				running = false;
			}

			if (key.getKind() == Key.Kind.ArrowRight) {
				if (x == columns - 2 && ((y == rows / 2) || (y == rows / 2 - 1))){
					//setRoom(x,y,terminal, columns + 1, rows);
					terminal.applyForegroundColor(Terminal.Color.WHITE);
					terminal.moveCursor(x,y);
					terminal.putCharacter('.');
					x = 1;
					//TODO: Test on school computers
					/*terminal.moveCursor(x,y);
					terminal.applyForegroundColor(Terminal.Color.GREEN);
					terminal.putCharacter('@');
					terminal.applyForegroundColor(Terminal.Color.WHITE);
					terminal.moveCursor(0,y);
					terminal.putCharacter('.');*/
				}
				if (x != columns - 2){
					terminal.applyForegroundColor(Terminal.Color.WHITE);
					terminal.moveCursor(x,y);
					terminal.putCharacter('.');
					terminal.moveCursor(x + 1,y);
					terminal.applyForegroundColor(Terminal.Color.GREEN);
					terminal.putCharacter('@');
					x++;
				}
			}
			if (key.getKind() == Key.Kind.ArrowLeft) {
				if (x != 1){
					terminal.applyForegroundColor(Terminal.Color.WHITE);
					terminal.moveCursor(x,y);
					terminal.putCharacter('.');
					terminal.moveCursor(x - 1,y);
					terminal.applyForegroundColor(Terminal.Color.GREEN);
					terminal.putCharacter('@');
					x--;
				}
			}
			if (key.getKind() == Key.Kind.ArrowUp) {
				if (y != 1){
					terminal.applyForegroundColor(Terminal.Color.WHITE);
					terminal.moveCursor(x,y);
					terminal.putCharacter('.');
					terminal.moveCursor(x,y - 1);
					terminal.applyForegroundColor(Terminal.Color.GREEN);
					terminal.putCharacter('@');
					y--;
				}
			}
			if (key.getKind() == Key.Kind.ArrowDown) {
				if (y != rows - 2){
					terminal.applyForegroundColor(Terminal.Color.WHITE);
					terminal.moveCursor(x,y);
					terminal.putCharacter('.');
					terminal.moveCursor(x,y + 1);
					terminal.applyForegroundColor(Terminal.Color.GREEN);
					terminal.putCharacter('@');
					y++;
				}
			}
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