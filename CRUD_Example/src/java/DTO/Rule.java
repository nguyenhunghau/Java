package DTO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class Rule {
    private int ID;
    private int nMaxStudent;
    private int OldMin;
    private int OldMax;
    private int nMaxSubject;
    private float Benchmark;

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the nMaxStudent
     */
    public int getnMaxStudent() {
        return nMaxStudent;
    }

    /**
     * @param nMaxStudent the nMaxStudent to set
     */
    public void setnMaxStudent(int nMaxStudent) {
        this.nMaxStudent = nMaxStudent;
    }

    /**
     * @return the OldMin
     */
    public int getOldMin() {
        return OldMin;
    }

    /**
     * @param OldMin the OldMin to set
     */
    public void setOldMin(int OldMin) {
        this.OldMin = OldMin;
    }

    /**
     * @return the OldMax
     */
    public int getOldMax() {
        return OldMax;
    }

    /**
     * @param OldMax the OldMax to set
     */
    public void setOldMax(int OldMax) {
        this.OldMax = OldMax;
    }

    /**
     * @return the nMaxSubject
     */
    public int getnMaxSubject() {
        return nMaxSubject;
    }

    /**
     * @param nMaxSubject the nMaxSubject to set
     */
    public void setnMaxSubject(int nMaxSubject) {
        this.nMaxSubject = nMaxSubject;
    }

    /**
     * @return the Benchmark
     */
    public float getBenchmark() {
        return Benchmark;
    }

    /**
     * @param Benchmark the Benchmark to set
     */
    public void setBenchmark(float Benchmark) {
        this.Benchmark = Benchmark;
    }
}
