package ${packageImport};

import ${basePackage}.base.mvp.BasePresenter;
import ${modelImport};
import ${viewImport};
import androidx.fragment.app.Fragment;

/**
 * @author liujiang
 * created at: ${.now}
 * Desc: ${desc}
 */
public class ${presenterName} extends BasePresenter<${modelName}, ${viewName}> {
	public ${presenterName}(${viewName} listener, Fragment fragment) {
        super(listener, fragment);
    }

    @Override
    public ${modelName} createModel() {
        return new ${modelName}(fragment);
    }
	
}
