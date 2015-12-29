package com.olafski.fastleafdecay;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.ClassWriter;
import static org.objectweb.asm.Opcodes.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FldTransformer implements IClassTransformer{
    Logger coreLogger = LogManager.getLogger("FastLeafDecay");

    @Override
    public byte[] transform(String obfName, String transformedName, byte[] basicClass) {

        if (transformedName.equals("net.minecraft.block.BlockLeavesBase")
                || transformedName.equals("mods.natura.blocks.trees.NLeaves")) // Natura
        {
            return patchLeafClass(basicClass);
        }

        return basicClass;
    }

    private byte[] patchLeafClass(byte[] basicClass)
    {
        coreLogger.log(Level.INFO, "Patching leaves.");

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode, 0);
        coreLogger.log(Level.INFO, "Found leaf Class: " + classNode.name);

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);

        String methodName = FldLoadingPlugin.IN_MCP ? "onNeighborBlockChange" : "func_149695_a";

        String worldClass = "net/minecraft/world/World";
        String blockClass = "net/minecraft/block/Block";

        String methodSignature = "(L" + worldClass + ";IIIL" + blockClass + ";)V";

        MethodVisitor mv = writer.visitMethod(ACC_PUBLIC, methodName, methodSignature, null, null);
        mv.visitCode();

        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitLineNumber(81, l0);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitVarInsn(ILOAD, 2);
        mv.visitVarInsn(ILOAD, 3);
        mv.visitVarInsn(ILOAD, 4);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESTATIC, "com/olafski/fastleafdecay/FldHandler", "handleLeafDecay", methodSignature);
        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitLineNumber(82, l1);
        mv.visitInsn(RETURN);
        Label l2 = new Label();
        mv.visitLabel(l2);
        mv.visitLocalVariable("this", "Lcom/olafski/fastleafdecay/FldTransformer;", null, l0, l2, 0);
        mv.visitLocalVariable("world", "L" + worldClass + ";", null, l0, l2, 1);
        mv.visitLocalVariable("x", "I", null, l0, l2, 2);
        mv.visitLocalVariable("y", "I", null, l0, l2, 3);
        mv.visitLocalVariable("z", "I", null, l0, l2, 4);
        mv.visitLocalVariable("block", "L" + blockClass + ";", null, l0, l2, 5);
        mv.visitMaxs(5, 6);
        mv.visitEnd();

        return writer.toByteArray();
    }
}
