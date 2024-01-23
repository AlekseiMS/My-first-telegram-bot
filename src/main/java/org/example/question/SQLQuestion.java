package org.example.question;

public class SQLQuestion extends AbstractQuestion{
    public SQLQuestion() {
        super ("Сколько в реаляционных (SQL) базах данных существует типов связей между таблицами?");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals("3");
    }
}
