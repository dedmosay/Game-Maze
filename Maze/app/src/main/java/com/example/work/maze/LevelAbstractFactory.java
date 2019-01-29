package com.example.work.maze;

public abstract class LevelAbstractFactory {                         // Абстрактная фабрика имеет бутылки и воду


    public abstract LevelAbstractProductScore createLevelScore();         // Создание воды

    public abstract LevelAbstractConatainer createContainer();       // Создание бутылки

}
