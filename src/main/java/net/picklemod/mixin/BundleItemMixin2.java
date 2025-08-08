package net.picklemod.mixin;

import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BundleContentsComponent.class)
public abstract class BundleItemMixin2 {

    @Inject(method = "getOccupancy(Lnet/minecraft/item/ItemStack;)Lorg/apache/commons/lang3/math/Fraction;", at = @At("RETURN"), cancellable = true)
    private static void modify(ItemStack stack, CallbackInfoReturnable<Fraction> cir) {

        Fraction original = cir.getReturnValue();

        if (stack.getMaxCount() == 1) {
            cir.setReturnValue(Fraction.getFraction(1, 4));
            System.out.println("Bundle 2 Returned: " + cir.getReturnValue());
            cir.cancel();
        }

        System.out.println("Bundle 2 Returned: " + cir.getReturnValue());


    }
}