package bilibili.lingaly.fabricmodregister;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRegister {
    /**
     * 用于注册Item
     * @param MOD_ID 你的模组ID
     * @param name 物品名称
     * @param item 传入一个物品
     * @return 注册完成的物品
     */
    public static Item registerItem(String MOD_ID, String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(MOD_ID, name), item);
    }

    /**
     * 用于注册Item
     * @param MOD_ID 你的模组ID
     * @param name 物品名称
     * @param itemGroup 物品类别
     * @return 注册完成的物品
     */
    public static Item registerItem(String MOD_ID, String name, ItemGroup itemGroup){
        return Registry.register(Registry.ITEM, new Identifier(MOD_ID, name),
                new Item(new FabricItemSettings().group(itemGroup)));
    }

    /**
     * 用于注册Block
     * @param MOD_ID 你的模组ID
     * @param name 方块名称
     * @param block 传入一个方块
     * @param itemGroup 方块类别
     * @return 注册完成的方块
     */
    public static Block registerBlock(String MOD_ID, String name, Block block, ItemGroup itemGroup){
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)));
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), block);
    }

    /**
     * 用于注册Block
     * @param MOD_ID 你的模组ID
     * @param name 方块名称
     * @param material Material类别(如: Material.METAL, Material.STONE等)
     * @param strength 方块硬度
     * @param requiresTool 是否需要特定工具
     * @param itemGroup 方块类别
     * @return
     */
    public static Block registerBlock(String MOD_ID, String name, Material material, Float strength, boolean requiresTool, ItemGroup itemGroup){
        Block block;
        if (requiresTool) {
            block = new Block(FabricBlockSettings.of(material).strength(strength).requiresTool());
        } else {
            block = new Block(FabricBlockSettings.of(material).strength(strength));
        }
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)));
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), block);
    }

    /**
     * 用于注册ItemGroup
     * @param MOD_ID 你的模组ID
     * @param name 物品类别名称
     * @param item 传入一个物品
     * @return 注册完成的ItemGroup
     */
    public static ItemGroup registerItemGroup(String MOD_ID, String name, Item item){
        return FabricItemGroupBuilder.build(new Identifier(MOD_ID, name),
                () -> new ItemStack(item));
    }

    private static FoodComponent foodComponent(int hunger, float saturationModifier, boolean alwaysEdible){
        FoodComponent.Builder foodComponent =  new FoodComponent.Builder()
                .hunger(hunger)
                .saturationModifier(saturationModifier);
        if (alwaysEdible){
            return foodComponent
                    .alwaysEdible()
                    .build();
        } else {
            return foodComponent
                    .build();
        }
    }

    /**
     *  创建一个不会使玩家获得效果的食物
     * @param MOD_ID 模组ID
     * @param name 食物名称
     * @param itemGroup 所在的物品类别
     * @param hunger 恢复的饥饿值
     * @param saturationModifier 恢复的饱食度
     * @param alwaysEdible 是否在满饥饿度情况下食用
     * @return 返回一个注册完的食物
     */
    public static Item registerFoodItem(String MOD_ID, String name, ItemGroup itemGroup, int hunger, float saturationModifier, boolean alwaysEdible){
        return Registry.register(Registry.ITEM,
                new Identifier(MOD_ID, name),
                new Item(new FabricItemSettings()
                        .group(itemGroup)
                        .food(foodComponent(hunger,
                                saturationModifier,
                                alwaysEdible))));
    }

    private static FoodComponent foodComponentwitheffect(int hunger,
                                                         float saturationModifier,
                                                         boolean alwaysEdible,
                                                         StatusEffect statusEffect,
                                                         int duration,
                                                         int amplifier,
                                                         float chance){
        FoodComponent.Builder foodComponent =  new FoodComponent.Builder()
                .hunger(hunger)
                .saturationModifier(saturationModifier)
                .statusEffect(new StatusEffectInstance(statusEffect, duration, amplifier), chance);
        if (alwaysEdible){
            return foodComponent
                    .alwaysEdible()
                    .build();
        } else {
            return foodComponent
                    .build();
        }
    }

    public static Item registerFoodItemWithEffect(String MOD_ID,
                                                  String name,
                                                  ItemGroup itemGroup,
                                                  int hunger,
                                                  float saturationModifier,
                                                  boolean alwaysEdible,
                                                  StatusEffect statusEffect,
                                                  int duration,
                                                  int amplifier,
                                                  float chance){
        return Registry.register(Registry.ITEM,
                new Identifier(MOD_ID, name),
                new Item(new FabricItemSettings()
                        .group(itemGroup)
                        .food(foodComponentwitheffect(hunger,
                                saturationModifier,
                                alwaysEdible,
                                statusEffect,
                                duration,
                                amplifier,
                                chance))));
    }
}
