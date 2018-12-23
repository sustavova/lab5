package pars;

public class calk {
    
    //  ќбъ€вление лексем
    final int NONE = 0;         //  FAIL
    final int DELIMITER = 1;    //  –азделитель(+-*/^=, ")", "(" )
    final int VARIABLE = 2;     //  ѕеременна€
    final int NUMBER = 3;       //  „исло
    
    //  ќбъ€вление констант синтаксических ошибок
    final int SYNTAXERROR = 0;  //  —интаксическа€ ошибка (10 + 5 6 / 1)
    final int UNBALPARENS = 1;  //  Ќесовпадение количества открытых и закрытых скобок
    final int NOEXP = 2;        //  ќтсутствует выражение при запуске анализатора
    final int DIVBYZERO = 3;    //  ќшибка делени€ на ноль
    
    //  Ћексема, определ€юща€ конец выражени€
    final String EOF = "\0";
    
    private String exp;     //  —сылка на строку с выражением
    private int explds;     //  “екущий индекс в выражении
    private String token;   //  —охранение текущей лексемы
    private int tokType;    //  —охранение типа лексемы
    
    
    //public String toString(){
        //return String.format("Exp = {0}\nexplds = {1}\nToken = {2}\nTokType = {3}", exp.toString(), explds,
                //token.toString(), tokType);
    //}
    
    //  ѕолучить следующую лексему
    private void getToken(){
        tokType = NONE;
        token = "";
        
        //  ѕроверка на окончание выражени€
        if(explds == exp.length()){
            token = EOF;
            return;
        }
        //  ѕроверка на пробелы, если есть пробел - игнорируем его.
        while(explds < exp.length() && Character.isWhitespace(exp.charAt(explds))) 
            ++ explds;
        //  ѕроверка на окончание выражени€
        if(explds == exp.length()){
            token = EOF;
            return;
        }
        if(isDelim(exp.charAt(explds))){
            token += exp.charAt(explds);
            explds++;
            tokType = DELIMITER;
        }
        else if(Character.isLetter(exp.charAt(explds))){
            while(!isDelim(exp.charAt(explds))){
                token += exp.charAt(explds);
                explds++;
                if(explds >= exp.length())
                    break;
                }
            tokType = VARIABLE;
        }
        else if (Character.isDigit(exp.charAt(explds))){
            while(!isDelim(exp.charAt(explds))){
                token += exp.charAt(explds);
                explds++;
                if(explds >= exp.length())
                    break;
                }
            tokType = NUMBER;
        }
        else {
            token = EOF;
            return;
        }
    }
 
    private boolean isDelim(char charAt) {
        if((" +-:*%=()".indexOf(charAt)) != -1)
            return true;
        return false;
    }
 
    //  “очка входа анализатора
    public BigFraction evaluate(String expstr) throws ParserException{

        BigFraction result = new BigFraction("0");
        
        exp = expstr;
        explds = 0;
        getToken();
        
        if(token.equals(EOF))
            handleErr(NOEXP);   //  Ќет выражени€
 
        //  јнализ и вычисление выражени€
        result = evalExp2();
        
        if(!token.equals(EOF))
            handleErr(SYNTAXERROR);
        
        return result;
    }
    
    //  —ложить или вычислить два терма
    private BigFraction evalExp2() throws ParserException{
        
        char op;
        BigFraction result;
        BigFraction partialResult;
        result = evalExp3();
        while((op = token.charAt(0)) == '+' || 
                op == '-'){
            getToken();
            partialResult = evalExp3();
            switch(op){
                case '-':
                    result = result.subtract(partialResult);
                    break;
                case '+':
                	result = result.add(partialResult);
                    break;
            }
        }
        return result;
    }
    
    //  ”множить или разделить два фактора
    private BigFraction evalExp3() throws ParserException{
        
        char op;
        BigFraction result;
        BigFraction partialResult;
        
        result = evalExp4();
        while((op = token.charAt(0)) == '*' || 
                op == ':'){
            getToken();
            partialResult = evalExp4();
            switch(op){
                case '*':
                    result = result.multiply(partialResult);
                    break;
                case ':':
                {
                    BigFraction zero = new BigFraction("0");
                      int ch = zero.compareTo(result);
                      if(ch == 0)
                        handleErr(DIVBYZERO);
                    result = result.divide(partialResult);
                    break;}
            }
        }
        return result;
    }
 
    //  ќпределить унарные + или -
    private BigFraction evalExp4() throws ParserException{
        BigFraction result;
        
        String op;
        op = " ";
        
        if((tokType == DELIMITER) && token.equals("+") ||
            token.equals("-")){
            op = token;
            getToken();
        }
        result = evalExp5();
        if(op.equals("-")) {
            BigFraction one = new BigFraction("-1");
            result =  result.multiply(one);}
        return result;
    }
    
    //  ќбработать выражение в скобках
    private BigFraction evalExp5() throws ParserException{
        BigFraction result;
        
        if(token.equals("(")){
            getToken();
            result = evalExp2();
            if(!token.equals(")"))
                handleErr(UNBALPARENS);
            getToken();
        }
        else
            result = atom();
        return result;
    }
    
    //  ѕолучить значение числа
    private BigFraction atom()   throws ParserException{

        BigFraction result = new BigFraction("0");
        switch(tokType){
            case NUMBER:
                try{
                    result = new BigFraction(token);
                }
                catch(NumberFormatException exc){
                    handleErr(SYNTAXERROR);
                }
                getToken();
    
            break;
            default:
                handleErr(SYNTAXERROR);
                break;
        }
        return result;
    }
    
    //   инуть ошибку
    private void handleErr(int nOEXP2) throws ParserException{
        
        String[] err  = {
                "Syntax error",
                "Unbalanced Parentheses",
                "No Expression Present",
                "Division by zero"
        };
        throw new ParserException(err[nOEXP2]);
    }
    
    
}