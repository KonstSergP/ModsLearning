package com.example.client.mixin;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.ShareToLanScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShareToLanScreen.class)
public abstract class ShareToLanScreenMixin extends Screen {

    // @Shadow позволяет нам получить доступ к приватной переменной
    // оригинального класса ShareToLanScreen, как будто мы внутри него.
    @Shadow private int port;

    // В MojMap текст называется Component
    protected ShareToLanScreenMixin(Component title) {
        super(title);
    }

    // Внедряемся в конструктор (в байт-коде он называется "<init>")
    // @At("TAIL") означает "выполни мой код в самом конце конструктора"
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInitConstructor(Screen lastScreen, CallbackInfo ci) {
        // Игра уже выбрала случайный порт, но мы нагло его перезаписываем!
        this.port = 25565;
    }
}
