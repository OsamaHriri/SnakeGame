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
import java.util.ArrayList;
import java.util.Iterator;


public class SysData {

    private static SysData single_instance = null;
    private ArrayList<Player> players;
    private ArrayList<Game> history;
    private ArrayList<Question> questions;


    public static SysData getInstance() {
        if (single_instance == null){
            single_instance = new SysData();
            single_instance.setQuestions();
        }

        return single_instance;
    }

    public void setQuestions() {
        this.questions = ReadFromJson();
    }

    public void setHistory(ArrayList<Game> history) {
        this.history = history;
    }

    public ArrayList<Game> getHistory() {
        return history;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public Question ifExists(String ID){
        Question temp=null;
        for(Question d:questions){
            if(d.getQuestion().equals(ID))
                temp=d;
        }
        return temp;
    }

    public boolean DeleteQuestion(String ID){
        Question temp= ifExists(ID);
        if(temp==null) return false;
        else {
            questions.remove(temp);
            return true;
        }
    }
    public boolean InsertQuestion(Question Q){
        Question temp= ifExists(Q.getQuestion());
        if(temp!=null) return false;
        else return questions.add(Q);
    }
    public boolean UpdateQuestion(String question , String Updated ,ArrayList<String> answer,String correctAns, String level, String team){
        if(ifExists(Updated)!=null && !question.equals(Updated)) return false;
        Question temp=ifExists(question);
      if( DeleteQuestion(question)!=true) return false;
        return questions.add(new Question(Updated , answer,correctAns, level,  team));
    }

    public ArrayList<Question> ReadFromJson() {
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

    public void WriteTojson(){
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

    public ArrayList<Question> ReadHistoryJson() {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Question> result= new ArrayList<>();
        try (FileReader reader = new FileReader("json/history.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject obj2=(JSONObject) obj;
            JSONArray arr = (JSONArray) obj2.get("history");
            Iterator<Object> iterator = arr.iterator();
            while (iterator.hasNext()) {
                JSONObject object = (JSONObject) iterator.next();
           //     result.add( new Question((String)object.get("playerId"),,(int)object.get("score") ,(String)object.get("level") ,(String)object.get("team") ));
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
