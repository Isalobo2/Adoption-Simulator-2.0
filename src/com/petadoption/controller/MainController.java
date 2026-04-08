package com.petadoption.controller;

import com.petadoption.model.Animal;
import com.petadoption.service.PetService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // ── FXML injected fields ──────────────────────────────────────────────
    @FXML private VBox   petListContainer;
    @FXML private Label  detailName;
    @FXML private Label  detailSubtitle;
    @FXML private Label  detailAge;
    @FXML private Label  detailWeight;
    @FXML private Label  detailBreed;
    @FXML private Label  detailStatus;
    @FXML private TextArea detailDesc;
    @FXML private Button adoptBtn;
    @FXML private Label  statusLabel;
    @FXML private Button btnAll;
    @FXML private Button btnDogs;
    @FXML private Button btnCats;

    // ── State ─────────────────────────────────────────────────────────────
    private final PetService service = new PetService();
    private Animal selectedPet = null;

    // ── Lifecycle ─────────────────────────────────────────────────────────

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAll();
    }

    // ── Filter button handlers ────────────────────────────────────────────

    @FXML
    public void showAll() {
        setActiveFilter(btnAll);
        loadPets(service.getAvailableAll());
    }

    @FXML
    public void showDogs() {
        setActiveFilter(btnDogs);
        loadPets(service.getAvailableDogs());
    }

    @FXML
    public void showCats() {
        setActiveFilter(btnCats);
        loadPets(service.getAvailableCats());
    }

    // ── Adopt button handler ──────────────────────────────────────────────

    @FXML
    public void handleAdopt() {
        if (selectedPet == null) return;

        // Prompt for adopter name
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Adopt " + selectedPet.getName());
        dialog.setHeaderText("Enter your name to adopt " + selectedPet.getName());
        dialog.setContentText("Your name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            if (name.isBlank()) {
                showAlert(Alert.AlertType.WARNING, "Name required", "Please enter your name to proceed.");
                return;
            }

            try {
                service.adopt(selectedPet.getId(), name);
                showAlert(Alert.AlertType.INFORMATION, "Adoption successful!",
                        "Congratulations! " + selectedPet.getName() + " is going home with " + name + "!");
                selectedPet = null;
                clearDetail();
                showAll(); // refresh the list
            } catch (RuntimeException e) {
                showAlert(Alert.AlertType.ERROR, "Adoption failed", e.getMessage());
            }
        });
    }

    // ── Internal helpers ──────────────────────────────────────────────────

    private void loadPets(List<Animal> pets) {
        petListContainer.getChildren().clear();
        selectedPet = null;
        clearDetail();

        if (pets.isEmpty()) {
            Label empty = new Label("No pets available in this category.");
            empty.getStyleClass().add("empty-label");
            petListContainer.getChildren().add(empty);
            statusLabel.setText("0 pets available");
            return;
        }

        for (Animal pet : pets) {
            petListContainer.getChildren().add(buildPetCard(pet));
        }

        statusLabel.setText(pets.size() + " pet" + (pets.size() == 1 ? "" : "s") + " available");
    }

    /**
     * Builds a clickable card for one pet.
     */
    private VBox buildPetCard(Animal pet) {
        VBox card = new VBox(4);
        card.getStyleClass().add("pet-card");

        // Top row: name + species badge
        HBox topRow = new HBox(8);
        topRow.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label(pet.getName());
        nameLabel.getStyleClass().add("pet-card-name");

        Label speciesBadge = new Label(capitalize(pet.getSpecies()));
        speciesBadge.getStyleClass().addAll("badge",
                pet.getSpecies().equals("dog") ? "badge-dog" : "badge-cat");

        topRow.getChildren().addAll(nameLabel, speciesBadge);

        // Bottom row: breed · age · weight
        Label infoLabel = new Label(
                pet.getBreed() + "  ·  " +
                pet.getAgeDisplay() + "  ·  " +
                pet.getWeightLbs() + " lbs"
        );
        infoLabel.getStyleClass().add("pet-card-info");

        card.getChildren().addAll(topRow, infoLabel);

        // Click to select
        card.setOnMouseClicked(e -> selectPet(pet, card));

        return card;
    }

    private void selectPet(Animal pet, VBox card) {
        // Deselect all cards
        petListContainer.getChildren().forEach(n -> n.getStyleClass().remove("selected"));
        card.getStyleClass().add("selected");

        selectedPet = pet;
        populateDetail(pet);
    }

    private void populateDetail(Animal pet) {
        detailName.setText(pet.getName());
        detailSubtitle.setText(capitalize(pet.getSpecies()) + "  ·  " + pet.getBreed());
        detailAge.setText(pet.getAgeDisplay());
        detailWeight.setText(pet.getWeightLbs() + " lbs");
        detailBreed.setText(pet.getBreed());
        detailStatus.setText("Available");
        detailStatus.setStyle("-fx-text-fill: #3B6D11;");
        detailDesc.setText(pet.getDescription() != null ? pet.getDescription() : "No description available.");
        adoptBtn.setText("Adopt " + pet.getName());
        adoptBtn.setDisable(false);
    }

    private void clearDetail() {
        detailName.setText("Select a pet");
        detailSubtitle.setText("");
        detailAge.setText("");
        detailWeight.setText("");
        detailBreed.setText("");
        detailStatus.setText("");
        detailDesc.setText("");
        adoptBtn.setText("Adopt");
        adoptBtn.setDisable(true);
    }

    private void setActiveFilter(Button active) {
        btnAll.getStyleClass().remove("active");
        btnDogs.getStyleClass().remove("active");
        btnCats.getStyleClass().remove("active");
        active.getStyleClass().add("active");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
