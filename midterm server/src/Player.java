public class Player {
    /**
     * isBeingKilled check mikonad ke bazikon morde ya na
     * username ra be har client midahad
     */
    private int people=10;
    private boolean isBeingKilled = false;
    private String username ;
    private int flag=0;

    /**
     * constructor
     * @param gamerUsername
     */
    public Player(String gamerUsername){
        username=gamerUsername;}

    public boolean isBeingKilled() {
        flag=1;
        return true;
    }

    /**
     *
     * @param flag har bar ke kasi bemirad mishavad 1 va dar gheire in soorat 0 ast
     */
    public void upDatePeople(int flag) {
        if (flag == 1)
            people=people-1;
        flag=0;
    }}


