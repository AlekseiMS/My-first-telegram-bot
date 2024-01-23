package org.example.question;

public class JavaQuustion extends AbstractQuestion{
    public JavaQuustion() {
        super("Сколько в языке программирования JAVA есть примитивов?");
    }

    @Override
    public boolean checkAnswer (String answer){
        return answer.equals("8");
    }
}
