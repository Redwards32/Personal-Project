import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Match {

	static int i = 0;

	public static void main(String[] args) {

		Student[] students = new Student[100];
		Scanner kbd = new Scanner(System.in);
		System.out.println("Enter file address: ");
		String fileName = kbd.next();
		kbd.close();

		try {
			Scanner input = new Scanner(new FileReader(fileName));

			while (input.hasNextLine()) {
				Scanner line = new Scanner(input.nextLine());
				line.useDelimiter("[\t\r]");
				String name = line.next();
				String gender = line.next();
				String birthDate = line.next();
				Scanner birthDateReader = new Scanner(birthDate);
				birthDateReader.useDelimiter("-");//Used for whitespace
				int month = birthDateReader.nextInt();
				int day = birthDateReader.nextInt();
				int year = birthDateReader.nextInt();
				int quietTimePreference = line.nextInt();
				int musicPreference = line.nextInt();
				int readingPreference = line.nextInt();
				int chattingPreference = line.nextInt();

				Date birth = new Date(year, month, day);
				Preference pref = new Preference(quietTimePreference, musicPreference, readingPreference,
						chattingPreference);
				Student studentAdd = new Student(name, gender.charAt(0), birth, pref);
				students[i++] = studentAdd;

				birthDateReader.close();
				line.close();
			}
			int max = i;

			for (int i = 0; i < max; i++) {
				if (!students[i].getMatched()) {
					int bestScore = 0, bestMatch = 0;
				for (int j = i + 1; j < max; j++) {
						if (!students[j].getMatched()) {
							int score = students[i].compare(students[j]);
							if (score > bestScore) {
								bestScore = score;
								bestMatch = j;
							}
						}
					}
					if (bestScore != 0) {
						students[i].setMatched(true);
						students[bestMatch].setMatched(true);
						System.out.println(students[i].getName() + " matches with " + students[bestMatch].getName()
								+ " with the score " + bestScore);
					} 
					else if (!students[i].getMatched()) {
						System.out.println(students[i].getName() + " has no matches.");
				}
				}
			}

			input.close();

		} 
		catch (FileNotFoundException e) { 
			System.out.println(e);//Error Handling
		} 
		catch (NoSuchElementException e) { 
			System.out.println(e);//Error Handling
		}
	}
}