/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huydat.data;

import huydat.server.Server;
import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author Admin
 */
 
public class frame {

    public static void setIconImage(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int[] idImg;
    public int[] dx;
    public int[] dy;
    public int[] onTop;
    public int[] flip;

 private int itemID;
    private Data stand;
    private Data move;
    private Data jumpUp, jumpDown;
    private Data dead;
    private Data behind;

    public MountData(int itemID) {
        this.itemID = itemID;
        this.stand = new Data();
        this.move = new Data();
        this.jumpDown = new Data();
        this.jumpUp = new Data();
        this.dead = new Data();
        this.behind = new Data();
    }

    public void addFrameStand(short icon) {
        stand.add(icon);
    }

    public void addFrameMove(short icon) {
        move.add(icon);
    }

    public void addFrameJumpUp(short icon) {
        jumpUp.add(icon);
    }

    public void addFrameJumpDown(short icon) {
        jumpDown.add(icon);
    }

    public void addFrameDead(short icon) {
        dead.add(icon);
    }

    public void addFrameBehind(short icon) {
        behind.add(icon);
    }

    public short[][] getData() {
        short[][] frames = new short[6][];
        frames[0] = stand.toArray();
        frames[1] = move.toArray();
        frames[2] = jumpUp.toArray();
        frames[3] = jumpDown.toArray();
        frames[4] = dead.toArray();
        frames[5] = behind.toArray();
        return frames;
    }

    private class Data {

        private final List<Short> frames = new ArrayList<>();

        private void add(short icon) {
            frames.add(icon);
        }

        private void remove(short icon) {
            frames.remove(icon);
        }

        private short[] toArray() {
            short[] frames = new short[this.frames.size()];
            int i = 0;
            for (short frame : this.frames) {
                frames[i++] = frame;
            }
            return frames;
        }
}
