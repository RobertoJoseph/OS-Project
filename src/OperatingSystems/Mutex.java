package OperatingSystems;

import java.util.*;

public class Mutex {

    int printKey = 1;
    int assignKey = 1;
    int readAndWriteFileKey = 1;
    Queue<String> userOutputBlockedQueue;
    Queue<String> userInputBlockedQueue;
    Queue<String> fileBlockedQueue;

    public Mutex() {
        userOutputBlockedQueue = new LinkedList<>();
        userInputBlockedQueue = new LinkedList<>();
        fileBlockedQueue = new LinkedList<>();
    }

    public Queue<String> getUserOutputBlockedQueue() {
        return userOutputBlockedQueue;
    }

    public void setUserOutputBlockedQueue(Queue<String> userOutputBlockedQueue) {
        this.userOutputBlockedQueue = userOutputBlockedQueue;
    }

    public void setUserInputBlockedQueue(Queue<String> userInputBlockedQueue) {
        this.userInputBlockedQueue = userInputBlockedQueue;
    }

    public void setFileBlockedQueue(Queue<String> fileBlockedQueue) {
        this.fileBlockedQueue = fileBlockedQueue;
    }

    public Queue<String> getUserInputBlockedQueue() {
        return userInputBlockedQueue;
    }

    public Queue<String> getFileBlockedQueue() {
        return fileBlockedQueue;
    }


    public boolean semWait(String a, int id, Interpreter interpreter, Scheduler scheduler) {
        String program;
        switch (a) {
            case "userOutput":
                if (printKey == 1) {
                    printKey--;
                    return true;
                } else {
                    program = interpreter.getPrograms().get(id).variable;
                    userOutputBlockedQueue.add(program);
                    interpreter.getBlockedQueue().add(program);
                    interpreter.setRunning(false);
                    Scheduler.currentProgram = "";
                }
                break;

            case "userInput":
                if (assignKey == 1) {
                    assignKey--;
                    return true;
                } else {
                    program = interpreter.getPrograms().get(id).variable;
                    userInputBlockedQueue.add(program);
                    interpreter.getBlockedQueue().add(program);
                    interpreter.setRunning(false);
                    Scheduler.currentProgram = "";
                }
                break;
            case "file":
                if (readAndWriteFileKey == 1) {
                    readAndWriteFileKey--;
                    return true;
                } else {
                    program = interpreter.getPrograms().get(id).variable;
                    fileBlockedQueue.add(program);
                    interpreter.getBlockedQueue().add(program);
                    interpreter.setRunning(false);
                    Scheduler.currentProgram = "";
                }
                break;
        }
        return false;
    }

    public void semSignal(String a, Interpreter interpreter) {
        String program;
        switch (a) {
            case "userOuput":
                printKey++;
                program = userOutputBlockedQueue.poll();
                if (!interpreter.getBlockedQueue().isEmpty()) {
                    interpreter.getBlockedQueue().remove();
                }
                interpreter.getReadyQueue().add(program);
                break;
            case "userInput":
                assignKey++;
                program = userInputBlockedQueue.poll();
                if (!interpreter.getBlockedQueue().isEmpty()) {
                    interpreter.getBlockedQueue().remove();
                }
                interpreter.getReadyQueue().add(program);
                break;

            case "file":
                readAndWriteFileKey++;
                program = fileBlockedQueue.poll();
                if (!interpreter.getBlockedQueue().isEmpty()) {
                    interpreter.getBlockedQueue().remove();
                }
                interpreter.getReadyQueue().add(program);
                break;
        }
    }

}
