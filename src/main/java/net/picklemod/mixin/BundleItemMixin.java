package net.picklemod.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.component.type.BundleContentsComponent;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BundleContentsComponent.class)
public abstract class BundleItemMixin {

    @Inject(method = "calculateOccupancy", at = @At("RETURN"), cancellable = true)

    private static void modifyOccupancy(List<ItemStack> stacks, CallbackInfoReturnable<Fraction> cir) {

        int count = 0;

        for (ItemStack itemStack : stacks) {
            if (itemStack.getMaxCount() == 1) {
                count += 16;
            } else if (itemStack.getMaxCount() == 16) {
                count += 16;
            } else {
                count += 1;
            }
        }

        cir.setReturnValue((Fraction.getFraction(count, 64)));
        System.out.println("Bundle Returned: " + cir.getReturnValue());

    }

}


