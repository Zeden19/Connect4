/*
Author: Arjun Sharma (251240847)
This class stores a key for a hashtable, the score and level for the game
Date: October 19, 2022
 */
public class Record {
    private String key;
    private int score;
    private int level;

    /**
     * Constructor:
     * @param key: key for the hashtable
     * @param score: the score of the game
     * @param level: The level of the game
     */
    public Record(String key, int score, int level){
         this.key = key;
         this.score = score;
         this.level = level;
    }

    /**
     * Returns the key value
     * @return the key value
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the score value
     */
    public int getScore() {
        return score;
    }

    /**
     * returns the level value
     * @return the level value
     */
    public int getLevel() {
        return level;
    }
}


