package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Question implements Serializable {

    private String question;
    private List<String> answers = null;
    private String correctAns;
    private String level;
    private String team;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 2895261579048435587L;

    /**
     * No args constructor for use in serialization
     */
    public Question() {
    }

    /**
     * @param question
     * @param level
     * @param answers
     * @param team
     * @param correctAns
     */
    public Question(String question, List<String> answers, String correctAns, String level, String team) {
        super();
        this.question = question;
        this.answers = answers;
        this.correctAns = correctAns;
        this.level = level;
        this.team = team;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Question withQuestion(String question) {
        this.question = question;
        return this;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Question withAnswers(List<String> answers) {
        this.answers = answers;
        return this;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public Question withCorrectAns(String correctAns) {
        this.correctAns = correctAns;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Question withLevel(String level) {
        this.level = level;
        return this;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Question withTeam(String team) {
        this.team = team;
        return this;
    }



}
