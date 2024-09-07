package com.orkva.utils.easy.excel;

import org.apache.poi.ss.usermodel.Cell;

import java.util.function.BiConsumer;

/**
 * CellValueBuilder
 *
 * @author Shepherd Xie
 * @version 2022/11/20
 */
public interface WriterCellBuilder<T> extends BiConsumer<Cell, T> {

    @Override
    void accept(Cell cell, T value);

    default Cell build(Cell cell, T value) {
        this.accept(cell, value);
        return cell;
    }

}
