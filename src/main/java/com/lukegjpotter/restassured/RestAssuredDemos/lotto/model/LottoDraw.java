package com.lukegjpotter.restassured.RestAssuredDemos.lotto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class LottoDraw {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private List<Integer> winningNumbers;
    private LocalDateTime drawDateTime;

    public LottoDraw() {
    }

    public LottoDraw(List<Integer> winningNumbers, LocalDateTime drawDateTime) {
        this.winningNumbers = winningNumbers;
        this.drawDateTime = drawDateTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public LocalDateTime getDrawDateTime() {
        return drawDateTime;
    }

    public void setDrawDateTime(LocalDateTime drawDateTime) {
        this.drawDateTime = drawDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoDraw lottoDraw = (LottoDraw) o;
        return Objects.equals(id, lottoDraw.id) && Objects.equals(winningNumbers, lottoDraw.winningNumbers) && Objects.equals(drawDateTime, lottoDraw.drawDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, winningNumbers, drawDateTime);
    }

    @Override
    public String toString() {
        return "LottoDraw {" +
                "id=" + id +
                ", winningNumbers=" + winningNumbers +
                ", drawDateTime=" + drawDateTime +
                '}';
    }
}
