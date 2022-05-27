package OperatingSystems;

public class PCB {
    int processID;
    State processState;
    int programCounter;
    int[] memoryBoundaries;
    public PCB(int processID, State processState, int programCounter, int[] memoryBoundaries) {
        this.processID = processID;
        this.processState = processState;
        this.programCounter = programCounter;
        this.memoryBoundaries = memoryBoundaries;
    }
    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }

    public State getProcessState() {
        return processState;
    }

    public void setProcessState(State processState) {
        this.processState = processState;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public int[] getMemoryBoundaries() {
        return memoryBoundaries;
    }

    public void setMemoryBoundaries(int[] memoryBoundaries) {
        this.memoryBoundaries = memoryBoundaries;
    }
}


