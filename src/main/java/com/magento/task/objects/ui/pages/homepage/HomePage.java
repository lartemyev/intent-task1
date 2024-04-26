package com.magento.task.objects.ui.pages.homepage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.magento.task.objects.ui.controls.CheckBox;
import com.magento.task.objects.ui.controls.MenuWithElements;
import com.magento.task.framework.localization.var1_locme.LocMe;
import com.magento.task.framework.utils.WebElementUtils;
import com.magento.task.objects.ui.pages.BasePage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class HomePage extends BasePage {

    public final static String NAME = "Home page";

    private final SelenideElement pageId = $x("//a[@class='block-promo home-main']");

    private final MenuWithElements gearMenu = new MenuWithElements($x("//a[@id='ui-id-6']/.."));
    public LocMe GEAR_MENU_BAGS_MENU_ITEM;



}