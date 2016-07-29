package easycal.com.easycal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: kailash.atal
 */
public class SemanticOperatorOperand {
    private static final Integer ADD=1;
    private static final Integer SUBTRACT_1=2;
    private static final Integer SUBTRACT_2=3;
    private static final Integer MULTIPLY=4;
    private static final Integer DIVIDE=5;
    private static final Integer PERCENT=6;

    private static Map<String,Integer> conceptToLexMapping= new HashMap<String,Integer>();

    static {
        conceptToLexMapping.put("add",1);
        conceptToLexMapping.put("plus",1);
        conceptToLexMapping.put("sum",1);

        conceptToLexMapping.put("subtract",2);
        conceptToLexMapping.put("minus",3);

        conceptToLexMapping.put("multiply",4);
        conceptToLexMapping.put("into",4);

        conceptToLexMapping.put("divide",5);
        conceptToLexMapping.put("by",5);
        conceptToLexMapping.put("divided",5);

        conceptToLexMapping.put("percent",6);
        conceptToLexMapping.put("per",6);
    }

    private static boolean isNumber(String word){
        for(char c: word.toCharArray()){
            if(!(c>='0' && c<='9')){
                return false;
            }
        }
        return true;
    }

    private static int[] getOperatorOperand(String text){
        int[] operatorOperand=new int[]{-1,-1,-1};

        String[] words = text.split("[ ]+");
        for(String word:words){
            Integer operator = conceptToLexMapping.get(word);
            if(operator!=null && operatorOperand[0]==-1){
                operatorOperand[0]=operator;
            }
            else if(isNumber(word)){
                if(operatorOperand[1]==-1){
                    operatorOperand[1]=Integer.parseInt(word);
                }else{
                    operatorOperand[2]=Integer.parseInt(word);
                }
            }

        }
        return operatorOperand;
    }

    private static long computeResult(int[] operatorOperand){
        switch(operatorOperand[0]){
            case 1:
                if(operatorOperand[1]!=-1 && operatorOperand[2]!=-1){
                    return operatorOperand[1]+operatorOperand[2];
                }
                break;
            case 2:
                if(operatorOperand[1]!=-1 && operatorOperand[2]!=-1){
                    return operatorOperand[2]-operatorOperand[1];
                }
                break;
            case 3:
                if(operatorOperand[1]!=-1 && operatorOperand[2]!=-1){
                    return operatorOperand[1]-operatorOperand[2];
                }
                break;
            case 4:
                if(operatorOperand[1]!=-1 && operatorOperand[2]!=-1){
                    return operatorOperand[1]*operatorOperand[2];
                }
                break;
            case 5:
                if(operatorOperand[1]!=-1 && operatorOperand[2]!=-1){
                    return operatorOperand[1]/operatorOperand[2];
                }
                break;
            case 6:
                if(operatorOperand[1]!=-1 && operatorOperand[2]!=-1){
                    return (operatorOperand[1]*operatorOperand[2])/100;
                }
                break;

        }
        return -10000000;
    }

    public static String getComputedResult(String text){
        int[] operatorOperand = getOperatorOperand(text);
        long computedResult = computeResult(operatorOperand);
        return computedResult == -10000000 ? null : computedResult + "";
    }
}
