package ${packageImport};

import ${presenterImport};
import ${viewImport};
import ${basePackage}.base.activity.BaseMvpFragment;
import ${basePackage}.databinding.${bindingName};


/**
 * @author liujiang
 * created at: ${.now}
 * Desc: ${desc}
 */
public class ${fragmentName} extends BaseMvpFragment<${bindingName}, ${presenterName}> implements ${viewName}{

    @Override
    protected ${bindingName} getViewBinding() {
        return ${bindingName}.inflate(getLayoutInflater());
    }

	@Override
    public ${presenterName} createPresenter() {
        return new ${presenterName}(this, this);
    }

    @Override
    public void initView() {
        
    }


  
}
