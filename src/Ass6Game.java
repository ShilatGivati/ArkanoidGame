/**
 * @Author Shilat Givati
 * ID 206377038
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The class  runs the program.
 */
public class Ass6Game {

    /**
     * Main function.
     *
     * @param args - The user's input.
     */
    public static void main(String[] args) {
        boolean bool = false;
        AnimationRunner animationRunner = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(animationRunner, animationRunner.getGui().getKeyboardSensor());
        List<LevelInformation> levelInformation = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case "1":
                    levelInformation.add(new DirectHit());
                    bool = true;
                    break;
                case "2":
                    levelInformation.add(new WideEasy());
                    bool = true;
                    break;
                case "3":
                    levelInformation.add(new Green3());
                    bool = true;
                    break;
                case "4":
                    levelInformation.add(new FinalFour());
                    bool = true;
                    break;
                default:
                    break;
            }
        }
        if (!bool) {
            levelInformation.add(new DirectHit());
            levelInformation.add(new WideEasy());
            levelInformation.add(new Green3());
            levelInformation.add(new FinalFour());
        }
        gameFlow.runLevels(levelInformation);
    }
}
