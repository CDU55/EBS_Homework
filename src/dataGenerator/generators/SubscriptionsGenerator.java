package dataGenerator.generators;

import dataGenerator.Utils;
import dataGenerator.models.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SubscriptionsGenerator {

    public List<String> generateSubscriptions(int[][] fieldsAssignmentMatrix, List<Field> fields)
    {
        List<String> result=new ArrayList<String>();
        Random r=new Random();
        List<String> currentResultComponents;
        int [] fieldsEqualityOperatorCount=new int[fields.size()];
        for(int fieldIndex=0;fieldIndex<fields.size();fieldIndex++)
        {
            int fieldAssignmentsCount=Utils.lineLength(fieldsAssignmentMatrix, fieldIndex);
            int minEqualityOperatorCount =
                    Utils.calculatePercentage(fieldAssignmentsCount, fields.get(fieldIndex).equalityFrequency);
            if (minEqualityOperatorCount == -1) {
                return null;
            }
            else
            {
                int currentFieldEqualityCount=minEqualityOperatorCount;
                if(minEqualityOperatorCount<fieldAssignmentsCount) {
                    currentFieldEqualityCount = r.nextInt(fieldAssignmentsCount - minEqualityOperatorCount) + minEqualityOperatorCount;
                }
                fieldsEqualityOperatorCount[fieldIndex]=currentFieldEqualityCount;
            }
        }
        Field currentFiled=null;
        for(int subscriptionIndex=0;subscriptionIndex<fieldsAssignmentMatrix[0].length;subscriptionIndex++) {
            currentResultComponents=new ArrayList<String>();
            for (int fieldIndex = 0; fieldIndex < fields.size(); fieldIndex++) {
                if(fieldsAssignmentMatrix[fieldIndex][subscriptionIndex]==1) {
                    currentFiled = fields.get(fieldIndex);
                    String name=currentFiled.name;
                    String value=currentFiled.getRandomValue();
                    String operator="";
                    if(fieldsEqualityOperatorCount[fieldIndex]>0)
                    {
                        operator=" = ";
                        fieldsEqualityOperatorCount[fieldIndex]--;
                    }
                    else
                    {
                        operator=" "+currentFiled.getRandomOperatorNoEqual()+" ";
                    }
                    String currentFiledCondition=name+operator+value;
                    currentResultComponents.add(currentFiledCondition);
                }
            }
            if(currentResultComponents.size()>0)
            {
                String currentSubscription=currentResultComponents.get(0);
                for(int conditionIndex=1;conditionIndex<currentResultComponents.size();conditionIndex++)
                {
                    currentSubscription=currentSubscription+" and "+currentResultComponents.get(conditionIndex);
                }
                result.add(currentSubscription);
            }
        }
        return result;
    }
}
