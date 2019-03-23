package com.dczajkowski.survey;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Named("Survey")
@SessionScoped
public class Survey implements Serializable {
    private int page = 1;
    private List<Ad> ads = Arrays.asList(
        new Ad("Buy a laptop", "https://laptop.com"),
        new Ad("Buy a car", "https://car.com")
    );

    // All
    private String name;
    private String email;
    private Integer age;
    private Sex sex;
    private Education education;
    private Integer height; // cm

    // Woman
    private Integer bustSize; // cm
    private BraSize braSize;
    private Integer waistLineSize; // cm
    private Integer hipsSize; // cm

    // Man
    private Integer chestSize; // cm
    private Integer waistSize; // cm

    // All page 2
    private String howMuchPerMonth;
    private String howOften;
    private List<String> whatColors = new ArrayList<>();
    private List<String> favouriteTypes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getBustSize() {
        return bustSize;
    }

    public void setBustSize(Integer bustSize) {
        this.bustSize = bustSize;
    }

    public BraSize getBraSize() {
        return braSize;
    }

    public void setBraSize(BraSize braSize) {
        this.braSize = braSize;
    }

    public Integer getWaistLineSize() {
        return waistLineSize;
    }

    public void setWaistLineSize(Integer waistLineSize) {
        this.waistLineSize = waistLineSize;
    }

    public Integer getHipsSize() {
        return hipsSize;
    }

    public void setHipsSize(Integer hipsSize) {
        this.hipsSize = hipsSize;
    }

    public Integer getChestSize() {
        return chestSize;
    }

    public void setChestSize(Integer chestSize) {
        this.chestSize = chestSize;
    }

    public Integer getWaistSize() {
        return waistSize;
    }

    public void setWaistSize(Integer waistSize) {
        this.waistSize = waistSize;
    }

    public boolean getIsFemale() {
        return sex == Sex.FEMALE;
    }

    public boolean getIsMale() {
        return sex == Sex.MALE;
    }

    public List<Sex> getAvailableSexes() {
        return new ArrayList<>(Arrays.asList(Sex.values()));
    }

    public List<Education> getAvailableEducationLevels() {
        return new ArrayList<>(Arrays.asList(Education.values()));
    }

    public List<BraSize> getAvailableBraSizes() {
        return new ArrayList<>(Arrays.asList(BraSize.values()));
    }

    public int getMinimumHeight() {
        return sex == Sex.FEMALE ? 150 : 165;
    }

    public int getMaximumHeight() {
        return sex == Sex.FEMALE ? 185 : 200;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void nextPage() {
        page += 1;

        if (page == 4) {
            System.out.print("Persist survey: ");
            System.out.println(this);
        }
    }

    public List<String> getAvailableClothingTypes() {
        return sex == Sex.FEMALE
            ? new ArrayList<>(Arrays.asList("Two-Piece Dresses", "Blouses", "Skirts", "Trousers"))
            : new ArrayList<>(Arrays.asList("Trousers", "Shorts", "Suits", "Shirts", "Ties"));
    }

    public String getHowMuchPerMonth() {
        return howMuchPerMonth;
    }

    public void setHowMuchPerMonth(String howMuchPerMonth) {
        this.howMuchPerMonth = howMuchPerMonth;
    }

    public String getHowOften() {
        return howOften;
    }

    public void setHowOften(String howOften) {
        this.howOften = howOften;
    }

    public List<String> getWhatColors() {
        return whatColors;
    }

    public void setWhatColors(List<String> whatColors) {
        this.whatColors = whatColors;
    }

    public List<String> getFavouriteTypes() {
        return favouriteTypes;
    }

    public void setFavouriteTypes(List<String> favouriteTypes) {
        this.favouriteTypes = favouriteTypes;
    }

    public Ad getRandomAd() {
        return ads.get(new Random().nextInt(ads.size()));
    }

    public void openAd(Ad ad) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(ad.withIncrementedClickCount().getLink());
    }
}
