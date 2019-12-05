package control;

import model.Game;
import model.Player;
import model.Question;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;


public class SysData {

    private static SysData single_instance = null;
    private ArrayList<Player> players;
    private ArrayList<Game> history=new ArrayList<>();
    private ArrayList<Question> questions;


    public static SysData getInstance() {
        if (single_instance == null){
            single_instance = new SysData();
            single_instance.setQuestions();
            single_instance.setHistory();
            single_instance.setPlayers();
        }

        return single_instance;
    }

    public void setPlayers() {
        this.players = readPlayerFromJson();
    }

    public void setQuestions() {
        this.questions = readQuestionFromJson();
    }

    public void setHistory() {
        this.history =readHistoryFromJson();
    }

    public ArrayList<Game> getHistory() {
        return history;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Question ifExists(String ID){
        Question temp=null;
        for(Question d:questions){
            if(d.getQuestion().equals(ID))
                temp=d;
        }
        return temp;
    }

    public boolean deleteQuestion(String ID){
        Question temp= ifExists(ID);
        if(temp==null) return false;
        else {
            questions.remove(temp);
            return true;
        }
    }
    public boolean insertQuestion(Question Q){
        Question temp= ifExists(Q.getQuestion());
        if(temp!=null) return false;
        else return questions.add(Q);
    }
    public boolean updateQuestion(String question , String Updated ,ArrayList<String> answer,String correctAns, String level, String team){
        if(ifExists(Updated)!=null && !question.equals(Updated)) return false;
        Question temp=ifExists(question);
      if( deleteQuestion(question)!=true) return false;
        return questions.add(new Question(Updated , answer,correctAns, level,  team));
    }

    public ArrayList<Question> readQuestionFromJson() {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Question> result= new ArrayList<>();
        try (FileReader reader = new FileReader("json/questionsjson.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject obj2=(JSONObject) obj;
            JSONArray arr = (JSONArray) obj2.get("questions");
            Iterator<Object> iterator = arr.iterator();
            while (iterator.hasNext()) {
                JSONObject object = (JSONObject) iterator.next();
                ArrayList<String>answers= (JSONArray)object.get("answers");
                result.add( new Question((String)object.get("question"),answers,(String)object.get("correct_ans") ,(String)object.get("level") ,(String)object.get("team") ));
            }
            return result;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeQuestionTojson(){
        JSONObject jObject = new JSONObject();
        try
        {
            JSONArray jArray = new JSONArray();
            for (Question Q : questions)
            {
                JSONObject Question = new JSONObject();
                Question.put("question", Q.getQuestion());
                JSONArray array= new JSONArray();
                array.add(Q.getAnswers());
                Question.put("answers",array);
                Question.put("correct_ans", Q.getCorrectAns());
                Question.put("level", Q.getLevel());
                Question.put("team", Q.getTeam());
                jArray.add(Question);
            }
            jObject.put("questions", jArray);
            Files.write(Paths.get("json/questionsjson.json"), jObject.toJSONString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
    } }

    public ArrayList<Game> readHistoryFromJson() {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Game> result= new ArrayList<>();
        try (FileReader reader = new FileReader("json/history.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject obj2=(JSONObject) obj;
            JSONArray arr = (JSONArray) obj2.get("history");
            Iterator<Object> iterator = arr.iterator();
            while (iterator.hasNext()) {
                JSONObject object = (JSONObject) iterator.next();
                result.add( new Game((String)object.get("playerId"),(int)(long)object.get("score"),(int)(long)object.get("numOfSouls") ,(String)object.get("date") ,(String)object.get("durationOfGame") ));
            }
            return result;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }}
    public void writeHistoryTojson(){
        JSONObject jObject = new JSONObject();
        try
        {
            JSONArray jArray = new JSONArray();
            for (Game h : history)
            {
                JSONObject play = new JSONObject();
                play.put("playerId",h.getPlayerId());
                play.put("score",h.getScore());
                play.put("numOfSouls",h.getNumOfSouls());
                play.put("date", h.getDate());
                play.put("durationOfGame", h.getDurationOfGame());
                jArray.add(play);
            }
            jObject.put("history", jArray);
            Files.write(Paths.get("json/history.json"), jObject.toJSONString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } }


    public ArrayList<Player> readPlayerFromJson() {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Player> result= new ArrayList<>();
        try (FileReader reader = new FileReader("json/players.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject obj2=(JSONObject) obj;
            JSONArray arr = (JSONArray) obj2.get("players");
            Iterator<Object> iterator = arr.iterator();
            while (iterator.hasNext()) {
                JSONObject object = (JSONObject) iterator.next();
                String dateStr = (String)object.get("dateOfBirth");
                SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                        Locale.ENGLISH);
              Date birthDate = new java.util.Date(sdf.parse(dateStr).getTime());
              long points=(long)object.get("maxPoints");
                result.add( new Player((String)object.get("personID"),(String)object.get("firstName"),(String)object.get("surName") , birthDate,(String)object.get("phone"),
                (String)object.get("email"),(String)object.get("password"),(int)points ));
            }
            return result;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void writePlayerTojson(){
        JSONObject jObject = new JSONObject();
        try
        {
            JSONArray jArray = new JSONArray();
            for (Player p : players)
            {
                JSONObject play = new JSONObject();
                play.put("personID", p.getPersonID());
                play.put("firstName",p.getFirstName());
                play.put("surName", p.getSurName());
                play.put("dateOfBirth", p.getDateOfBirth().toString());
                play.put("phone", p.getPhone());
                play.put("email", p.getEmail());
                play.put("password", p.getPassword());
                play.put("maxPoints", p.getMaxPoints());
                jArray.add(play);
            }
            jObject.put("players", jArray);
            Files.write(Paths.get("json/players.json"), jObject.toJSONString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } }



   /* public void InsertToJson(Question Q){
        JSONParser jsonParser = new JSONParser();

        try {
           FileReader file = new FileReader("json/questionsjson.json");
            Object obj = jsonParser.parse(file);
            JSONObject obj1=(JSONObject)obj;
            JSONArray Arr= (JSONArray) obj1.get("questions");
            JSONObject Question = new JSONObject();
            Question.put("question", Q.getQuestion());
            JSONArray array= new JSONArray();
            array.add(Q.getAnswers());
            Question.put("answers",array);
            Question.put("correct_ans", Q.getCorrectAns());
            Question.put("level", Q.getLevel());
            Question.put("team", Q.getTeam());
            Arr.add(Question);
            JSONObject sampleObject= new JSONObject();
            sampleObject.put("questions",Arr);

            Files.write(Paths.get("json/questionsjson.json"), sampleObject.toJSONString().getBytes());
            questions.add(Q);



        } catch (ParseException | IOException e) {
            e.printStackTrace();

    }}

    public void DeleteFromJson(String ID){
       Question temp=null;
        for(Question d:questions){

            if(d.getQuestion().equals(ID))
                temp=d;
        }
        System.out.println(temp);
        questions.remove(temp);
        System.out.println(questions);
        JSONObject jObject = new JSONObject();
        try
        {
            JSONArray jArray = new JSONArray();
            for (Question Q : questions)
            {
                JSONObject Question = new JSONObject();
                Question.put("question", Q.getQuestion());
                JSONArray array= new JSONArray();
                array.add(Q.getAnswers());
                Question.put("answers",array);
                Question.put("correct_ans", Q.getCorrectAns());
                Question.put("level", Q.getLevel());
                Question.put("team", Q.getTeam());
                jArray.add(Question);
            }
            jObject.put("questions", jArray);
            Files.write(Paths.get("json/questionsjson.json"), jObject.toJSONString().getBytes());

    } catch (IOException e) {
            e.printStackTrace();
        }}


        public void UpdateToJson(String Question,ArrayList<String> answers,String corrent_ans,String level , String Team){
         Question temp=null;
         for(Question Q : questions){
             if(Q.getQuestion().equals(Question)){
                 temp=Q;
             } }
          Q.S


        }*/


    }
