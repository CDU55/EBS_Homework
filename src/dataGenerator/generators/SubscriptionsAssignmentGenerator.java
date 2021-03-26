package dataGenerator.generators;

import dataGenerator.Utils;

import java.util.Arrays;

public class SubscriptionsAssignmentGenerator {

    private int[][] solution;
    private boolean solutionFound;
    private int remainingAssignations;
    private int[] emptySubscriptions;

    private void generateSolution(int[][] currentSolution,int currentFieldIndex,int currentSubscriptionIndex,int[] fieldsCount,int[] availableFieldAssignations)
    {
        if(solutionFound==true)
        {
            return;
        }
        if(remainingAssignations<currentEmptySubscriptionsNumber())
        {
            return;
        }
        for(int value: new int[]{1, 0}) {
            if (value == 0 || (value == 1 && availableFieldAssignations[currentFieldIndex] > 0)) {
                if(value==1)
                {
                    availableFieldAssignations[currentFieldIndex]--;
                    remainingAssignations--;
                    emptySubscriptions[currentSubscriptionIndex]=1;
                }
                if(value==0 && currentSolution[currentFieldIndex][currentSubscriptionIndex]==1)
                {
                    availableFieldAssignations[currentFieldIndex]++;
                    remainingAssignations++;
                    if(Utils.columnLength(currentSolution,currentSubscriptionIndex)==1)
                    {
                        emptySubscriptions[currentSubscriptionIndex]=0;
                    }
                }
                currentSolution[currentFieldIndex][currentSubscriptionIndex] = value;
                if (isValid(currentSolution)) {
                    if (isSolution(currentSolution, fieldsCount)) {
                        solution = new int[currentSolution.length][currentSolution[0].length];
                        for (int lineIndex = 0; lineIndex < currentSolution.length; lineIndex++) {
                            for (int columnIndex = 0; columnIndex < currentSolution[0].length; columnIndex++) {
                                solution[lineIndex][columnIndex] = currentSolution[lineIndex][columnIndex];
                            }
                        }
                        solutionFound = true;
                    }
                } else {
                    if (currentSubscriptionIndex == currentSolution[0].length - 1) {
                        if (currentFieldIndex < currentSolution.length - 1) {
                            generateSolution(currentSolution, currentFieldIndex + 1, 0, fieldsCount,availableFieldAssignations);
                        }
                    } else {
                        generateSolution(currentSolution, currentFieldIndex, currentSubscriptionIndex + 1, fieldsCount,availableFieldAssignations);
                    }
                }
            }
        }
    }


    private boolean isValid(int[][] matrix)
    {
        for(int currentColumn=0;currentColumn<matrix[0].length;currentColumn++)
        {
            if(Utils.columnLength(matrix,currentColumn)<1)
            {
                return false;
            }
        }
        return true;
    }

    private boolean isSolution(int[][] matrix,int[] fieldsCount)
    {
        for(int currentLine=0;currentLine<matrix.length;currentLine++)
        {
            if(Utils.lineLength(matrix,currentLine)!=fieldsCount[currentLine])
            {
                return false;
            }
        }
        return true;
    }

    private int currentEmptySubscriptionsNumber()
    {
        int empty=0;
        for(int i=0;i<emptySubscriptions.length;i++)
        {
            if(emptySubscriptions[i]==0)
            {
                empty++;
            }
        }
        return empty;
    }

    public int[][] getAssignment(int fieldsCount, int subscriptionCount, int[] fieldsSubscriptionNumber)
    {
        solutionFound=false;
        solution=null;
        emptySubscriptions=new int[subscriptionCount];
        remainingAssignations=0;
        for(int i=0;i<fieldsSubscriptionNumber.length;i++)
        {
            remainingAssignations+=fieldsSubscriptionNumber[i];
        }
        int[] availableFieldAssignations= Arrays.copyOf(fieldsSubscriptionNumber, fieldsSubscriptionNumber.length);
        int[][] currentSolution=new int[fieldsCount][subscriptionCount];
        generateSolution(currentSolution,0,0,fieldsSubscriptionNumber,availableFieldAssignations);
        return solution;
    }
}
