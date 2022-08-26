package ${packageImport};

import ${basePackage}.base.mvp.BasePresenter;
import ${modelImport};
import ${viewImport};
import android.content.Context;

/**
 * @author liujiang
 * created at: ${.now}
 * Desc: ${desc}
 */
public class ${presenterName} extends BasePresenter<${modelName}, ${viewName}> {
	public ${presenterName}(${viewName} listener, Context context) {
        super(listener, context);
    }

    @Override
    public ${modelName} createModel() {
        return new ${modelName}(context);
    }
	
}
