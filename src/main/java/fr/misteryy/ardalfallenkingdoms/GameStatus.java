package fr.misteryy.ardalfallenkingdoms;

public enum GameStatus {
    ATTENTE,
    GAME,
    ASSAUTS,
    DEATHMATCH;
    private static GameStatus currentStatus;
    public static boolean isStatus(GameStatus status){
        return currentStatus == status;
    }
    public static GameStatus getStatus(){
        return currentStatus;
    }
    public static void setStatus(GameStatus currentStatus){
        GameStatus.currentStatus = currentStatus;
    }
}
