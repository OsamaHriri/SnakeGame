package view;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import control.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import model.Player;
import model.Question;
import model.QuestionLevel;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;


import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class QManagement implements Initializable {

    @FXML
    Button btnDelete;
    @FXML
    ComboBox<String> ComboDelete;
    @FXML
    TextArea QuestionBody;
    @FXML
    Button btnUpdate;
    @FXML
    ComboBox<String> ComboChooseQuestion;
    @FXML
    ComboBox<String> ComboUpdateTeam;
    @FXML
    ComboBox<String> ComboUpdateLevel;
    @FXML
    TextField UpdateQuestionBody;
    @FXML
    TextField UpdateAnswer1;
    @FXML
    TextField UpdateAnswer2;
    @FXML
    TextField UpdateAnswer3;
    @FXML
    TextField UpdateAnswer4;
    @FXML
    ComboBox<Integer> ComboUpdateCorrectAns;

    @FXML
    Button btnInsert;
    @FXML
    TextField InsertQuestionBody;
    @FXML
    ComboBox<String> CoInsertteams;
    @FXML
    ComboBox<String> CoInsertlevel;
    @FXML
    TextField InsertAnswer1;
    @FXML
    TextField InsertAnswer2;
    @FXML
    TextField InsertAnswer3;
    @FXML
    TextField InsertAnswer4;
    @FXML
    ComboBox<Integer> CoInsertCorrectAnswer;

    public void glow(MouseEvent mouse) {
        BorderPane b = (BorderPane) mouse.getSource();
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.GREEN);
        borderGlow.setHeight(83.52);
        borderGlow.setRadius(43.6075);
        borderGlow.setSpread(0.6);
        borderGlow.setWidth(92.91);
        b.setEffect(borderGlow);

    }


    @FXML
    public void clearGlow(MouseEvent mouse) {
        BorderPane b = (BorderPane) mouse.getSource();
        b.setEffect(null);
    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        //  ComboDelete.add
        ArrayList<Question> ArrQ = SysData.getInstance().getQuestions();
        for (Question Q : ArrQ) {
            String s = Q.getQuestion();
            ComboDelete.getItems().add(s);
            ComboChooseQuestion.getItems().add(s);
        }

        String[] teams = {"Chimp", "Crocodile", "Scorpion", "Giraffe", "Spider", "Viper", "Panther", "Wolf", "Sloth", "Lion", "Panda", "Piranha", "Rabbit", "Shark", "Hawk", "Husky"};
        ComboUpdateTeam.getItems().addAll(teams);
        CoInsertteams.getItems().addAll(teams);

        ArrayList<String> levels = new ArrayList<>();
        QuestionLevel[] values = QuestionLevel.values();
        for (QuestionLevel ql : values)
            levels.add(ql.toString());
        ComboUpdateLevel.getItems().addAll(levels);
        CoInsertlevel.getItems().addAll(levels);


        ArrayList<Integer> CoreectAns = new ArrayList<>();
        CoreectAns.add(1);
        CoreectAns.add(2);
        CoreectAns.add(3);
        CoreectAns.add(4);

        CoInsertCorrectAnswer.getItems().addAll(CoreectAns);
        ComboUpdateCorrectAns.getItems().addAll(CoreectAns);

        ComboDelete.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    QuestionBody.setText(SysData.getInstance().getQuestion(newValue).toString());
                }
        );
    }


    @FXML
    public void UpdateQ(ActionEvent event) {
        if (event.getSource().equals(btnUpdate)) {
            System.out.println("x");
            String UpDateThisValue = ComboChooseQuestion.getValue();
            if (UpDateThisValue != null) {
                if (SysData.getInstance().ifExists(UpDateThisValue)) {
                    Question UpdateThisQ = null;
                    for (Question Q : SysData.getInstance().getQuestions()) {
                        if (Q.getQuestion().equals(UpDateThisValue)) {
                            UpdateThisQ = Q;
                        }
                    }
                    if (UpdateThisQ != null) {
                        ArrayList<String> UpdatedAnswers = new ArrayList<>();

                        int CorrectAns = ComboUpdateCorrectAns.getValue();
                        String GetTeamValue = ComboUpdateTeam.getValue();
                        String GetLevelValue = ComboUpdateLevel.getValue();
                        String UpdateBody = UpdateQuestionBody.getText();
                        String UpdateA1 = UpdateAnswer1.getText();
                        String UpdateA2 = UpdateAnswer2.getText();
                        String UpdateA3 = UpdateAnswer3.getText();
                        String UpdateA4 = UpdateAnswer4.getText();
                        if (UpdateBody != "" && UpdateA1!= "" && UpdateA2 != ""  && UpdateA3 != "" && UpdateA4 != "" && GetTeamValue != null && GetLevelValue != "" && CorrectAns != 0)
                            UpdatedAnswers.add(UpdateA1);
                        UpdatedAnswers.add(UpdateA2);
                        UpdatedAnswers.add(UpdateA3);
                        UpdatedAnswers.add(UpdateA4);
                        String CorrectAnswer = UpdatedAnswers.get(CorrectAns);
                        if (SysData.getInstance().updateQuestion(UpDateThisValue, UpdateBody, UpdatedAnswers, CorrectAnswer, GetLevelValue, GetTeamValue))
                            System.out.print("zzzz");

                    }


                }
            }

        }
    }
        @FXML
        public void DeleteQ(ActionEvent event)
        {
            if (event.getSource().equals(btnDelete))
            {
                String OnDeleteQ = ComboDelete.getValue();
                if (OnDeleteQ != null) {
                    QuestionBody.setText(OnDeleteQ);
                    System.out.println(OnDeleteQ);
                    Question DeleteThisQ = null;
                    if (SysData.getInstance().ifExists(OnDeleteQ)) {
                        for (Question Q : SysData.getInstance().getQuestions()) {
                            if (Q.getQuestion().equals(OnDeleteQ)) {
                                DeleteThisQ = Q;
                                System.out.println(DeleteThisQ.getAnswers());
                            }
                            if (DeleteThisQ != null) {
                                System.out.print("yesright");
                                if(SysData.getInstance().deleteQuestion(OnDeleteQ))
                                System.out.println("loo");



                            }
                    }

                    }

                }


            }


        }

        @FXML
        public void populateFields(ActionEvent event) {
        if(event.getSource().equals(ComboChooseQuestion)) {
            System.out.println(ComboChooseQuestion.getValue());
            String ComboValue = ComboChooseQuestion.getValue();
            Question MyQuestion = null;
            for (Question q : SysData.getInstance().getQuestions()) {
                if (q.getQuestion().equals(ComboValue)) {
                    MyQuestion = q;
                }
            }
            if (MyQuestion != null) {
                System.out.println("loe");
                System.out.println(MyQuestion.getTeam());
                UpdateQuestionBody.setPromptText(ComboValue);
                UpdateQuestionBody.setText(ComboValue);
                ComboUpdateTeam.setValue(MyQuestion.getTeam());
                ComboUpdateLevel.setValue(MyQuestion.getLevel().toString());
                UpdateAnswer1.setText(MyQuestion.getAnswers().get(0));
                UpdateAnswer2.setText(MyQuestion.getAnswers().get(1));
                UpdateAnswer3.setText(MyQuestion.getAnswers().get(2));
                UpdateAnswer4.setText(MyQuestion.getAnswers().get(3));
                ComboUpdateCorrectAns.setValue(new Integer(MyQuestion.getCorrectAns()));
//                ArrayList<String> myAnswers = new ArrayList<>();
//                myAnswers.addAll(MyQuestion.getAnswers());
//                System.out.println(myAnswers);
//                String ans1=myAnswers.get(0);
//                UpdateAnswer1.setText(myAnswers.get(1));
//                UpdateAnswer2.setPromptText(myAnswers.get(2));
//                String ans2=myAnswers.get(1);
//                String ans3=myAnswers.get(2);
//                String ans4=myAnswers.get(3);
//                System.out.println(ans1 + ans2 + ans3 + ans4);
//                String Correct = MyQuestion.getCorrectAns();
//                QuestionLevel Level = MyQuestion.getLevel();
//                String team = MyQuestion.getTeam();
//                UpdateAnswer1.setText(ans1);
//                UpdateAnswer2.setText(ans2);
//                UpdateAnswer3.setText(ans3);
//                UpdateAnswer4.setText(ans4);
//
//                System.out.println(ans1 + ans2 + ans3 + ans4);


            }
        }

            // get questions from SysData
            // find the correct question
            //get el inputs, fields combo boxwes, bla bla bla and set value
        }

        @FXML
        public void InsertQ(MouseEvent event){
                System.out.print("i am here ya laila");
                String InsertThisQuestion = InsertQuestionBody.getText();
                if (!InsertThisQuestion.equals("") ) {
                    String T = CoInsertteams.getValue();
                    String L = CoInsertlevel.getValue();
                    String An1 = InsertAnswer1.getText();
                    String An2 = InsertAnswer2.getText();
                    String An3 = InsertAnswer3.getText();
                    String An4 = InsertAnswer4.getText();
                    String CorrectAnsNum = CoInsertCorrectAnswer.getValue().toString();
                    System.out.println("shlav 2");
                    if (T != null && L != null && An1 != null && An2 != null && An3 != null && An4 != null && CorrectAnsNum != "")
                    {
                        System.out.println("SysData.getInstance().insertQuestion(InserQuestion)");
                        ArrayList<String> newAnsInsert = new ArrayList<>();  //answers array
                        newAnsInsert.add(An1);
                        newAnsInsert.add(An2);
                        newAnsInsert.add(An3);
                        newAnsInsert.add(An4);

                        QuestionLevel level = null;
                        System.out.print(L);
                        switch (L) {
                            case "ONE":
                                level = QuestionLevel.ONE;
                            case "TWO":
                                level = QuestionLevel.TWO;
                            case "THREE":
                                level = QuestionLevel.THREE;
                        }


                        Question InserQuestion = new Question(InsertThisQuestion, newAnsInsert, CorrectAnsNum, level, T);
                        System.out.println(SysData.getInstance().insertQuestion(InserQuestion));
                        System.out.println(SysData.getInstance().getQuestions());
                }
            }
        }
    }













