package com.example;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class VillagerDialogueScreen extends Screen {

    private TextFieldWidget inputField;
    private String aiResponse = "";
    private ButtonWidget sendButton;

    public VillagerDialogueScreen() {
        super(Text.literal("Диалог с жителем"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        inputField = new TextFieldWidget(
                this.textRenderer, centerX - 100, centerY - 20, 200, 20, Text.literal("Введите сообщение")
        );
        inputField.setMaxLength(200);
        this.addDrawableChild(inputField);

        sendButton = ButtonWidget.builder(Text.literal("Отправить"), button -> {
            String userInput = inputField.getText();
            sendMessageToAI(userInput);
        }).dimensions(centerX - 40, centerY + 10, 80, 20).build();

        this.addDrawableChild(sendButton);
    }

    private void sendMessageToAI(String message) {
        // Мокаем ответ AI
        aiResponse = "AI ответ: \"" + message + "\"";
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Рендерим фон
        this.renderBackground(context, mouseX, mouseY, delta);

        // Заголовок
        Text titleText = this.title;
        int titleX = (this.width - textRenderer.getWidth(titleText)) / 2;
        context.drawTextWithShadow(this.textRenderer, titleText, titleX, 20, 0xFFFFFF);

        // Рендер компонентов
        super.render(context, mouseX, mouseY, delta);

        // Ответ AI
        Text responseText = Text.literal(aiResponse);
        int responseX = (this.width - textRenderer.getWidth(responseText)) / 2;
        context.drawTextWithShadow(this.textRenderer, responseText, responseX, this.height / 2 + 40, 0xAAAAAA);

        // Рендер поля ввода
        inputField.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (inputField.keyPressed(keyCode, scanCode, modifiers) || inputField.isFocused()) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return inputField.charTyped(chr, modifiers);
    }
}
