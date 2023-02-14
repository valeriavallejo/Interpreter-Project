/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exprevaluator;

/**
 *
 * @author veeva
 */
public class EvaluatorTester {

    public static void main(String[] args) {
        Evaluator anEvaluator = new Evaluator();
        for (String arg : args) {
            System.out.println(arg + " = " + anEvaluator.eval(arg));
        }

    }
}