package com.TubesPBO.World;

import com.TubesPBO.Entity.Creature.Player;
import com.TubesPBO.Entity.EntityManager;
import com.TubesPBO.Entity.Static.Grave;
import com.TubesPBO.Entity.Static.Tree;
import com.TubesPBO.Game.Game;
import com.TubesPBO.Game.Handler;
import com.TubesPBO.Tile.Tiles;
import com.TubesPBO.Utility.Utils;

import java.awt.*;

public class World {
    private int width,height;
    private int spawnX,spawnY;
    private int[][] tile;
    protected Handler handler;
    private EntityManager entityManager;
    public World(Handler handler, String path){
        this.handler=handler;
        entityManager= new EntityManager(handler,new Player(handler,0,0));
        loadEntity();
        loadWorld(path);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }
    public void update(){
        entityManager.update();
    }
    public void render(Graphics g){
        // rendering efficiency (tiles akan di render hanya yg terleihat oleh window user)
        int xStart=(int)Math.max(0,handler.getGameCamera().getxOffset()/Tiles.tileWidth);                          // 0 itu start dari ujung kiri world dan xoffset untuk kamera dari user / tiles widht agar mendapat besarnya dari tiles
        int xEnd=(int)Math.min(width,(handler.getGameCamera().getxOffset()+handler.getWidth())/Tiles.tileWidth+1);
        int yStart=(int)Math.max(0,handler.getGameCamera().getyOffset()/Tiles.tileHeight);
        int yEnd=(int)Math.min(height,(handler.getGameCamera().getyOffset()+handler.getHeight())/Tiles.tileHeight+1);
        //end
        for (int y=yStart; y<yEnd;y++){
            for(int x=xStart;x<xEnd;x++){
                getTile(x,y).render(g,(int)(x*Tiles.tileWidth-handler.getGameCamera().getxOffset()),(int)(y*Tiles.tileHeight-handler.getGameCamera().getyOffset()));
            }
        }
        entityManager.render(g);
    }
    
    public Tiles getTile(int x,int y){
        //mecegah player keluar dr map
//        if(x<0|| y<0||x >=width||y>=height){
//            return Tiles.grassTile;
//        }
        Tiles tile= Tiles.tiles[this.tile[x][y]];  //
        if(tile==null){
            return Tiles.brickTile;
        }
        return  tile;
    }
    public void loadEntity(){
        entityManager.addEntity(new Tree(handler,50,0));
        entityManager.addEntity(new Tree(handler,0,120));
        entityManager.addEntity(new Grave(handler,30,240));
        entityManager.addEntity(new Tree(handler,0,250));
        entityManager.addEntity(new Tree(handler,0,380));
        entityManager.addEntity(new Tree(handler,0,430));
        entityManager.addEntity(new Tree(handler,60,380));
        entityManager.addEntity(new Tree(handler,120,380));
        entityManager.addEntity(new Tree(handler,178,380));
        entityManager.addEntity(new Grave(handler,238,445));
        entityManager.addEntity(new Grave(handler,238,520));
        entityManager.addEntity(new Grave(handler,385,445));
        entityManager.addEntity(new Tree(handler,0,450));
        entityManager.addEntity(new Tree(handler,0,480));
        entityManager.addEntity(new Tree(handler,0,530));
        entityManager.addEntity(new Tree(handler,0,580));
        entityManager.addEntity(new Tree(handler,0,630));
        entityManager.addEntity(new Tree(handler,0,680));
        entityManager.addEntity(new Tree(handler,0,730));
        entityManager.addEntity(new Tree(handler,0,780));
        entityManager.addEntity(new Tree(handler,0,830));
        entityManager.addEntity(new Tree(handler,0,880));
        entityManager.addEntity(new Tree(handler,0,930));

        entityManager.addEntity(new Grave(handler,250,250));
        entityManager.addEntity(new Grave(handler,515,250));
        entityManager.addEntity(new Grave(handler,630,250));
        entityManager.addEntity(new Grave(handler,515,70));
        entityManager.addEntity(new Tree(handler,570,0));
        entityManager.addEntity(new Grave(handler,630,70));
        entityManager.addEntity(new Tree(handler,685,0));
        entityManager.addEntity(new Grave(handler,570,370));
        entityManager.addEntity(new Grave(handler,685,370));
        entityManager.addEntity(new Grave(handler,830,315));
        entityManager.addEntity(new Grave(handler,888,315));
        entityManager.addEntity(new Grave(handler,978,315));
        entityManager.addEntity(new Grave(handler,1036,315));
        entityManager.addEntity(new Grave(handler,1126,315));
        entityManager.addEntity(new Grave(handler,1184,315));
        entityManager.addEntity(new Grave(handler,1454,65));
        entityManager.addEntity(new Grave(handler,1454,135));
        entityManager.addEntity(new Tree(handler,1515,0));
        entityManager.addEntity(new Grave(handler,1576,65));
        entityManager.addEntity(new Grave(handler,1576,135));
        entityManager.addEntity(new Tree(handler,1855,0));
        entityManager.addEntity(new Tree(handler,1855,75));
        entityManager.addEntity(new Tree(handler,1855,135));
        entityManager.addEntity(new Tree(handler,1855,135));
        entityManager.addEntity(new Tree(handler,1855,255));
        entityManager.addEntity(new Tree(handler,1855,320));
        entityManager.addEntity(new Tree(handler,1855,405));
        entityManager.addEntity(new Tree(handler,1855,430));
        entityManager.addEntity(new Grave(handler,1454,375));
        entityManager.addEntity(new Grave(handler,1454,440));
        entityManager.addEntity(new Grave(handler,1454,510));
        entityManager.addEntity(new Grave(handler,1520,510));
    }
    public void loadWorld(String path){
        String file= Utils.loadFileAsString(path);
        String[] token= file.split("\\s+");
        width=Utils.parseInt(token[0]);
        height=Utils.parseInt(token[1]);
        spawnX=Utils.parseInt(token[2]);
        spawnY=Utils.parseInt(token[3]);

        tile=new int[width][height];
        for (int y=0; y<height;y++){
            for (int x=0;x<width;x++){
                tile[x][y]=Utils.parseInt(token[(x+y*width)+4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
